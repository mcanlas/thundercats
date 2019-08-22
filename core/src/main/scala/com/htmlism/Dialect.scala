package com.htmlism

trait Dialect {
  def libraryDependency: List[String]

  def imports: List[String]
}
