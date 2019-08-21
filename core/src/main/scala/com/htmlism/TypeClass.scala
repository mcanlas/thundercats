package com.htmlism

trait TypeClass

case class TypeClassKindStar(name: String, supers: TypeClassKindStar*) extends TypeClass

case class TypeClassKindStarStar(name: String, supers: TypeClassKindStarStar*) extends TypeClass
