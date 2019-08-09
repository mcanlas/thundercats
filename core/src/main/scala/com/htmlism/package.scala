package com

package object htmlism {
  val Semigroup = TypeClassKindStar()
  val Monoid    = TypeClassKindStar(Semigroup)

  val Functor     = TypeClassKindStarStar()
  val Applicative = TypeClassKindStarStar(Functor)
  val Monad       = TypeClassKindStarStar(Applicative)
}
