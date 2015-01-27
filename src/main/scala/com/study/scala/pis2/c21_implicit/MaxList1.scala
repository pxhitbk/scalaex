package com.study.scala.pis2.c21_implicit

/**
 * @author Hung
 */
class Person(val age: Int) extends Ordered[Person] {
  def compare(that: Person): Int = {
    age - that.age
  }
}

object MaxList1 {

  def maxListUpBound[T <: Ordered[T]](element: List[T]): T = {
    element match {
      case List() =>
        throw new IllegalArgumentException("empty list!")
      case List(x) => x
      case x :: rest =>
        val maxRest = maxListUpBound(rest)
        if (x > maxRest) x
        else maxRest
    }
  }

  def maxList[T <% Ordered[T]](elements: List[T]): T =
    elements match {
      case List() =>
        throw new IllegalArgumentException("empty list!")
      case List(x) => x
      case x :: rest =>
        val maxRest = maxList(rest) // (orderer) is implicit
        if (x > maxRest) x // orderer(x) is implicit
        else maxRest
    }

  def maxListImpParm[T](elements: List[T])(implicit orderer: T => Ordered[T]): T = {

    elements match {
      case List() =>
        throw new IllegalArgumentException("empty list!")
      case List(x) => x
      case x :: rest =>
        val maxRest = maxListImpParm(rest)(orderer)
        if (orderer(x) > maxRest) x
        else maxRest
    }
  }
}
