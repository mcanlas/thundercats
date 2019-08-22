package com.htmlism

sealed abstract class Kind(val expr: String)

object KindOption extends Kind("Option")

object KindList extends Kind("List")


