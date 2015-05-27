package com.study.scala.fpis.c3_functionaldatastructures

/**
 * Created by md101 on 5/23/15.
 */
sealed class List[+A]
case object Nil extends List[Nothing]
case class Cons[+A](head: A, trail: List[A]) extends List[A]

object List {

  def sum(v: List[Int]): Int = v match {
    case Nil => 0
    case Cons(x, xs) => x + sum(xs)
  }

  def product(d: List[Double]): Double = d match {
    case Nil => 0.0
    case Cons(0.0, _) => 1.0
    case Cons(x, xs) => x * product(xs)
  }

  def apply[A](as: A*): List[A] = {
    if(as.isEmpty) {
      println("as: Nil")
      Nil
    }
    else {
      println("as: " + as)
      Cons[A](as.head, apply(as.tail: _*))
      // _* notation tells the compiler to pass each element of arr as its own argument to apply(),
      // rather than all of it as a single argument.
    }
  }
}
object Main {
  def main(args: Array[String]) {
    val example = Cons(1, Cons(2, Cons(3, Nil)))
    println("example: " + example)
    val example1 = List(1, 2, 3)
    println("example1: " + example1)
    val total = List.sum(example)
    println("total of example = " + total)

    val x = List(1,2,3,4,5) match {
      case Cons(x, Cons(2, Cons(4, _))) => x
      case Nil => 42
      case Cons(x, Cons(y, Cons(3, Cons(4, _)))) => x + y
      case Cons(h, t) => h + List.sum(t)
      case _ => 101
    }

    println("x = " + x)
  }
}