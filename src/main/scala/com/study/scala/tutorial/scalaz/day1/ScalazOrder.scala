package com.study.scala.tutorial.scalaz.day1

import scalaz._
import Scalaz._
/**
 * Ord is for types that have an ordering. Ord covers all the standard comparing functions such as >, <, >= and <=.
 */
object ScalazOrder extends App {
  1 > 2.0
  //res8: Boolean = false

  //1 gt 2.0 => compilation failure

 /*
  * Order enables ?|? syntax which returns Ordering: LT, GT, and EQ.
  * It also enables lt, gt, lte, gte, min, and max operators by declaring order method.
  * Similar to Equal, comparing Int and Doubl fails compilation.
  * */
  1.0 ?|? 2.0
  //res10: scalaz.Ordering = LT

  1.0 max 2.0
  //res11: Double = 2.0
}
