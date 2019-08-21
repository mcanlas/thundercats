package com

package object htmlism {
  val Semigroup = TypeClassKindStar("Semigroup")
  val Monoid    = TypeClassKindStar("Monoid", Semigroup)

  val Functor     = TypeClassKindStarStar("Functor")
  val Applicative = TypeClassKindStarStar("Applicative", Functor)
  val Monad       = TypeClassKindStarStar("Monad", Applicative)
}
