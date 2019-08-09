package com.htmlism

trait TypeClass

case class TypeClassKindStar(supers: TypeClassKindStar*) extends TypeClass

case class TypeClassKindStarStar(supers: TypeClassKindStarStar*) extends TypeClass
