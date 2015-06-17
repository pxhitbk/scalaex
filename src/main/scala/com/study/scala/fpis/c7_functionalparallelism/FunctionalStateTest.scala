package com.study.scala.fpis.c7_functionalparallelism

/**
 * Created by g10-macmini on 6/17/15.
 */
object FunctionalStateTest extends App {
  println(RNG.simple(1))
  println(RNG.simple(1))
  println(RNG.simple(1))
}

trait RNG {
  /**
   * General-purpose function describe state actions that transform RNG states,
   * and these state actions can be built up and combined.
   * */
  type Rand[+A] = RNG => (A, RNG) //Type alias for a function.

  val int: Rand[Int] = _.nextInt()

  def unit[A](a: A): Rand[A] = self => (a, self)

  /**
   * Transforming the output of a state action without modifying the state itself
   * @param s
   * @param f
   * @tparam A
   * @tparam B
   * @return
   */
  def map[A, B](s: Rand[A])(f: A => B): Rand[B] = { rng =>
    val (b, rng2) = s(rng)
    (f(b), rng2)
  }

  def positiveMax(n: Int): Rand[Int] = { self =>
    def gen(rNG: RNG): (Int, RNG) = {
      val r = positiveInt(rNG)
      if (r._1 < n) r
      else gen(r._2)
    }
    gen(self)
  }

  def nextInt(): (Int, RNG)

  def positiveInt(rgn: RNG): (Int, RNG)
}

object RNG {
  def simple(seed: Long): RNG = new RNG {
    def nextInt() = {
      val seed2 = (seed * 0x5DEECE66DL + 0xBL) & ((1L << 48) - 1)
      ((seed2 >>> 16).asInstanceOf[Int], simple(seed2))
    }

    override def positiveInt(rgn: RNG): (Int, RNG) = {
      val (i, r) = rgn.nextInt()
      (Math.abs(i), r)
    }
  }
}