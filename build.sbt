lazy val core =
  project
    .settings(commonSettings: _*)
    .settings(libraryDependencies += "org.typelevel" %% "cats-core" % "2.0.0-M4")

lazy val dialectCats =
  (project in file("dialect-cats"))
    .settings(commonSettings: _*)

lazy val dialectScalaz =
  (project in file("dialect-scalaz"))
    .settings(commonSettings: _*)

lazy val commonSettings = List(
  scalaVersion := "2.13.0")

