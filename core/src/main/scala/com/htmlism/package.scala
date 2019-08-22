package com

package object htmlism {
  val Semigroup = TypeClassForStar("Semigroup")
  val Monoid    = TypeClassForStar("Monoid", Semigroup)

  val Functor     = TypeClassForStarStar("Functor")
  val Applicative = TypeClassForStarStar("Applicative", Functor)
  val Monad       = TypeClassForStarStar("Monad", Applicative)
}
