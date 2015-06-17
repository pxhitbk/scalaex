package com.study.scala.fpis.c7_functionalparallelism

/**
 * Created by g10-macmini on 6/17/15.
 */
object FunctionalStateTest extends App {
  println(RGN.simple(1))
  println(RGN.simple(1))
  println(RGN.simple(1))
}

trait RGN {
  def nextInt(): (Int, RGN)

  def positiveInt(rgn: RGN): (Int, RGN)
}

object RGN {
  type Rand[+A] = RGN => (A, RGN)

  val int: Rand[Int] = _.nextInt()

  def simple(seed: Long): RGN = new RGN {
    def nextInt() = {
      val seed2 = (seed * 0x5DEECE66DL + 0xBL) & ((1L << 48) - 1)
      ((seed2 >>> 16).asInstanceOf[Int], simple(seed2))
    }

    override def positiveInt(rgn: RGN): (Int, RGN) = {
      val (i, r) = rgn.nextInt()
      (Math.abs(i), r)
    }
  }
}