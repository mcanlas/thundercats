package com.htmlism

sealed trait Fact

case class TypeClassForStarHasInstance(typeClass: TypeClassForStar, plainType: PlainType) extends Fact

case class TypeClassForStarStarHasInstance(typeClass: TypeClassForStarStar, kind: Kind) extends Fact

case class TypeClassExtends(base: TypeClass, parent: TypeClass) extends Fact

case object CompanionCanSummon extends Fact
