package com.study.scala.fpis.c7_functionalparallelism

/**
 * Created by md101 on 5/30/15.
 */
object MainObj{

  def main(args: Array[String]) {
    val arr = Array(1,2,3,4,5,6,7,8,9,10)
    sum(arr)
  }

  /**
   * Unlike the foldLeft-based implementation,
   * this implementation can be parallelized—the two halves can be summed in parallel
   * @param as sequense to be summerized
   * @return sum value
   */
  def sum(as: IndexedSeq[Int]): Int = {
    println("sum for " + as)
    if(as.size <= 1) as.headOption getOrElse 0
    else {
      val (l, r) = as.splitAt(as.length / 2)
      sum(l) + sum(r)
    }
  }
}
