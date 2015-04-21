package com.study.scala.tutorial.scalaz.day1

import scalaz._
import Scalaz._
/**
 * Enum members are sequentially ordered types — they can be enumerated.
 * The main advantage of the Enum typeclass is that we can use its types in list ranges.
 * They also have defined successors and predecessors, which you can get with the succ and pred functions.
 */
object ScalazEnum extends App {
   'a' to 'e'
  //res30: scala.collection.immutable.NumericRange.Inclusive[Char] = NumericRange(a, b, c, d, e)

  /* Instead of the standard to,
    Enum enables |-> that returns a List by declaring pred and succ method on top of Order typeclass.
    There are a bunch of other operations it enables like
    -+-, ---, from, fromStep, pred, predx, succ, succx, |-->, |->, |==>, and |=>.
    It seems like these are all about stepping forward or backward, and returning ranges.
    */

   'a' |-> 'e'
  //res31: List[Char] = List(a, b, c, d, e)

   3 |=> 5
  //res32: scalaz.EphemeralStream[Int] = scalaz.EphemeralStreamFunctions$$anon$4@6a61c7b6

   'B'.succ
  //res33: Char = C
}
