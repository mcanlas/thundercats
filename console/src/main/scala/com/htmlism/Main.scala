package com.htmlism

import cats.effect._
import cats.implicits._

object Main extends App {
  private val monoids = List(Semigroup, Monoid)
  private val dialects = List(CatsDialect, ScalazDialect)

  def runDialect(d: Dialect): IO[Unit] =
    new SbtProjectTester[IO](d)
      .test

  dialects
    .traverse(runDialect)
    .unsafeRunSync()
}
