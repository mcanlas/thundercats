package com.htmlism

import java.nio.file.attribute.PosixFilePermission

import scala.sys.process._

import better.files.{ Dispose, File }
import cats.data._
import cats.effect._
import cats.implicits._

import better.files.Dsl.chmod_+

trait CodeTesterAlg[F[_]] {
  def test: F[Unit]
}

class SbtProjectTester[F[_]](implicit F: Sync[F]) extends CodeTesterAlg[F] {
  def test: F[Unit] =
    makeProjectDirectoryResource
      .use((useProjectDirectoryResource _).tupled)
      .void

  private def releaseTemporaryFile(f: File) =
    F.delay {
      println("would clean " + f.toString)
//      f.toTemporary.foreach(_ => ())
    }

  private def makeProjectDirectoryResource =
    for {
      f               <- makeResource(File.newTemporaryDirectory())
      buildFile       <- makeResource(f / "build.sbt")
      buildProperties <- makeResource(f / "project" / "build.properties")
      runner          <- makeResource(f / "runner.sh")
    } yield (f, buildFile, buildProperties, runner)

  private def useProjectDirectoryResource(root: File, buildFile: File, buildProperties: File, runner: File) =
    writeBuildSbtFile(buildFile) *>
      writeSbtVersion(buildProperties) *>
      writeSbtRunner(root)(runner) >>= makeExecutable >>= runFile

  private def makeResource(f: File) =
    Resource.make(F.delay(f))(releaseTemporaryFile)

  private def writeBuildSbtFile(f: File) =
    F.delay {
      f
        .appendLine("""scalaVersion := "2.13.0"""")
    }

  private def writeSbtVersion(f: File) =
    F.delay {
      f
        .createIfNotExists(createParents = true)
        .appendLine("sbt.version=1.3.0-RC4")
    }

  private def writeSbtRunner(root: File)(f: File) =
    F.delay {
      f
        .appendLine("#!/usr/bin/env bash")
        .appendLine(s"cd ${root.path}")
        .appendLine("exit 1")
    }

  private def makeExecutable(f: File) =
    F.delay {
      chmod_+(PosixFilePermission.OWNER_EXECUTE, f)
    }

  private def runFile(f: File) =
    F.delay {
      Seq(f.pathAsString).!!
    }
}
