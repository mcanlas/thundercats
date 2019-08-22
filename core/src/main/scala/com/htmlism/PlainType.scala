package com.htmlism

sealed abstract class PlainType(val expr: String)

case object PlaintTypeInt extends PlainType("Int")

case object PlaintTypeString extends PlainType("String")
