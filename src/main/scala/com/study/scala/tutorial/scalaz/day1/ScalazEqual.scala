package com.study.scala.tutorial.scalaz.day1

import scalaz._
import Scalaz._
/**
 * Eq is used for types that support equality testing. The functions its members implement are == and /=.
 * Instead of the standard ==, Equal enables ===, =/=,
 * and assert_=== syntax by declaring equal method.
 * The main difference is that === would fail compilation if you tried to compare Int and String.
 */
class ScalazEqual extends App {
  1 === 1

  1.some =/= 2.some

  1 =/= 2
}
