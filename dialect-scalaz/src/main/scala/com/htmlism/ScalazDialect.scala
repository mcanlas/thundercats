package com.htmlism

case object ScalazDialect extends Dialect {
  def libraryDependency: List[String] =
    List("org.scalaz", "scalaz-core", "7.2.28")

  def imports: List[String] =
    List("scalaz._, Scalaz._")
}
