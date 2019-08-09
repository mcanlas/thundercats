package com.htmlism

import java.nio.file.attribute.PosixFilePermission

import better.files.{ Dispose, File }
import cats.effect._
import cats.implicits._

import better.files.Dsl.chmod_+

trait CodeTesterAlg[F[_]] {
  def test: F[Unit]
}

class SbtProjectTester[F[_]](implicit F: Sync[F]) extends CodeTesterAlg[F] {
  def test: F[Unit] =
    makeResource(File.newTemporaryDirectory())
      .use(useProjectDirectoryResource)
      .void

  private def releaseTemporaryFile(f: File) =
    F.delay {
      println("would clean " + f.toString)
//      f.toTemporary.foreach(_ => ())
    }

  private def useProjectDirectoryResource(f: File) =
    makeResource(f / "build.sbt")
      .use(writeBuildSbtFile) *>
    makeResource(f / "project" / "build.properties")
      .use(writeSbtVersion) *>
    makeResource(f / "runner.sh")
      .use(writeSbtRunner(f)) >>= makeExecutable

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
        .appendLine("sbt.version=1.3.0-RC3")
    }

  private def writeSbtRunner(root: File)(f: File) =
    F.delay {
      f
        .appendLine("#!/usr/bin/env bash")
        .appendLine(s"cd ${root.path}")
        .appendLine("sbt test")
    }

  private def makeExecutable(f: File) =
    F.delay {
      chmod_+(PosixFilePermission.OWNER_EXECUTE, f)
    }
}
