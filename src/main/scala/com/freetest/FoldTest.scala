package com.freetest

/**
 * Created by g10-macmini on 5/6/15.
 */
object FoldTest extends App {
  val l = List(1, 2, 3, 4)
  val sl = List("a", "b", "c", "d")
  l.foldLeft(0)((a, b) => a + b)

  val m = Map(1 -> "a", 2 -> "b", 3 -> "c")
  m.foldLeft("")((a, b) => b match {
    case (k, v) => v + a
  })

  m.foldLeft(""){case (a, (k, v)) => a + v}

}
