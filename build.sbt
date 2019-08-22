lazy val root = Project("thundercats", file("."))
  .settings(commonSettings: _*)
  .aggregate(core, `dialect-cats`, `dialect-scalaz`)

lazy val core =
  project
    .settings(commonSettings: _*)
    .settings(libraryDependencies += "org.typelevel" %% "cats-effect" % "2.0.0-RC1")
    .settings(libraryDependencies += "com.github.pathikrit" %% "better-files" % "3.8.0")

lazy val `dialect-cats` =
  project
    .dependsOn(core)
    .settings(commonSettings: _*)

lazy val `dialect-scalaz` =
  project
    .dependsOn(core)
    .settings(commonSettings: _*)

lazy val commonSettings = List(
  scalaVersion := "2.13.0")

