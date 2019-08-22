package com.htmlism

case object CatsDialect extends Dialect {
  def libraryDependency: List[String] =
    List("org.typelevel", "cats-core", "2.0.0-RC1")

  def imports: List[String] =
    List("import cats._, cats.data._, cats.implicits._")
}
