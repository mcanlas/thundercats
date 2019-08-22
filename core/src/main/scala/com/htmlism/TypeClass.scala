package com.htmlism

trait TypeClass {
  def name: String
}

case class TypeClassForStar(name: String, supers: TypeClassForStar*) extends TypeClass

case class TypeClassForStarStar(name: String, supers: TypeClassForStarStar*) extends TypeClass
