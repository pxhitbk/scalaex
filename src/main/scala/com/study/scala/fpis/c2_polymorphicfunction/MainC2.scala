package com.study.scala.fpis.c2_polymorphicfunction

import scala.annotation.tailrec

/**
 * @author Hung
 */
object MainC2 {
  def main(args: Array[String]): Unit = {
    val arr: Array[Double] = Array(9.0, 10.5, 22, 7.0, 14.5, 14, 55, 12, 15, 7.0)
//    val v = binarySearch(arr, 22d)
//    val v1 = binarySearch1(arr, 7.0)
//    val v2 = PolymorphicFunctions.binarySearch(arr, 22d, (d1: Double, d2: Double) => d1 > d2)
//    println("Found: " + v1)
    def greater(a: Double, b: Double): Boolean = a > b
    println(isSorted(arr, greater))
  }


  def isSorted[A](as: Array[A], gt:(A,A) => Boolean): Boolean = {
    def go(n: Int): Boolean = {
      if(n >= as.length - 1) true
      else if (!gt(as(n), as(n+1))) false
      else go(n + 1)
    }
    go(0)
  }

  def partital1[A,B,C](a: A, f:(A,B) => C): B => C = { b =>
    f(a,b)
  }

  def currying[A,B,C](f:(A,B) => C): A => (B => C) = { a =>
    (b) => f(a, b)
  }

  def uncurry[A,B,C](f: A => B => C): (A,B) => C = { (a,b) =>
    f(a)(b)
  }

  def compose[A,B,C](f: B => C, g: A => B): A => C = {
    (a) => f(g(a))
  }

  // First, a binary search implementation, specialized to `Double`,
  // another primitive type in Scala, representing 64-bit floating
  // point numbers
  // Ideally, we could generalize this to work for any `Array` type,
  // so long as we have some way of comparing elements of the `Array`
  def binarySearch(ds: Array[Double], key: Double): Int = {
    @annotation.tailrec
    def go(low: Int, mid: Int, high: Int): Int = {
      if (low > high) -mid - 1
      else {
        val mid2 = (low + high) / 2
        val d = ds(mid2) //index into an array using the same syntax as function application
        if (d == key) mid2
        else if (d > key) go(low, mid2, mid2 - 1)
        else go(mid2 + 1, mid2, high)
      }
    }
    go(0, 0, ds.length - 1)
  }
  
  type Index = Int
  
  def binarySearch1(ds: Array[Double], key: Double): Option[Index] = {
      @tailrec
      def go(lo: Index, hi: Index): Option[Index] = {
        if (lo > hi)
          None
        else {
          val mid: Index = lo + (hi - lo) / 2
          ds(mid) match {
            case mv if (mv == key) => Some(mid)
            case mv if (mv <= key) => go(mid + 1, hi)
            case _ => go(lo, mid - 1)
          }
        }
      }
      go(0, ds.size - 1)
  }
}

object PolymorphicFunctions {
  // Here's a polymorphic version of `binarySearch`, parameterized on
  // a function for testing whether an `A` is greater than another `A`.
  def binarySearch[A](as: Array[A], key: A, gt: (A, A) => Boolean): Int = {
    @annotation.tailrec
    def go(low: Int, mid: Int, high: Int): Int = {
      if (low > high) -mid - 1
      else {
        val mid2 = (low + high) / 2
        val a = as(mid2)
        val greater = gt(a, key)
        if (!greater && !gt(key, a)) mid2
        else if (greater) go(low, mid2, mid2 - 1)
        else go(mid2 + 1, mid2, high)
      }
    }
    go(0, 0, as.length - 1)
  }
}