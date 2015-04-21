package com.study.scala.tutorial.scalaz.day0

//import scalaz._
//import Scalaz._

/**
 * If we were to write a function that sums two types using the Monoid, we need to call it like this: <br/>
 * <CODE> scala> def plus[A: Monoid](a: A, b: A): A = implicitly[Monoid[A]].mappend(a, b) <CODE>
 * We would like to provide an operator.
 * But we don’t want to enrich just one type, but enrich all types that has an instance for Monoid.
 * We will do this in Scalaz 7 style.
 */
object scalazex {
  /** If we were to write a function that sums two types using the Monoid, we need to call it like this: */
  def plus[A: Monoid](a: A, b: A): A = implicitly[Monoid[A]].mappend(a, b)
  //plus: [A](a: A, b: A)(implicit evidence$1: Monoid[A])A

  /** We would like to provide an operator.
    * But we don’t want to enrich just one type, but enrich all types that has an instance for Monoid.
    * We will do this in Scalaz 7 style.
    */
  trait MonoidOp[A] {
    val F: Monoid[A]
    val value: A
    /** Do plus */
    def |+|(a2: A) = F.mappend(value, a2)
  }

  implicit def toMonoidOp[A: Monoid](a: A): MonoidOp[A] = new MonoidOp[A] {
    override val F: Monoid[A] = implicitly[Monoid[A]]
    override val value: A = a
  }

  object Main extends App {
    import scalaz._
    import Scalaz._
    4 |+| 4
    "hu" |+| "ng"

    //scalaz

//    import Scalaz._

    // 1 will be converted to OptionIdOps[Int](1) by implicit method ToOptionIdOps[A](a: A) = new OptionIdOps(a)
    // do call method OptionIdOps[Int](1).some to wrap 1 into Option type
    1.some | 2 // equal: Some(1).getOrElse(2)
    (1 > 10) ? "Home" | "away" //equal: if(1 > 10) "Home" else "away"
  }

}
