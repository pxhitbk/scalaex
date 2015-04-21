package com.study.scala.tutorial.scalaz

/**
 * Created by md101 on 4/16/15.
 */
package object day0 {
  object SubtypePolymorphism {
    //First definition to think of a function plus that can add two values of type A
    def plus0[A](a1: A, a2: A): A = ???

    /** provide different definition for what it
      * means to add them. One way to achieve this is through subtyping.
      */
    trait Plus[A] {
      def plus(a2: A): A
    }

    /**
     * We can at least provide different definitions of plus for A.
     * But, this is not flexible since trait Plus needs to be mixed in at the time of defining the datatype.
     * So it can’t work for Int and String.
     */
    def plus1[A <: Plus[A]](a1: A, a2: A): A = a1.plus(a2)
  }

  /** Approach in Scala is to provide an implicit conversion or implicit parameters for the trait. */
  object AdhocPolymorphism {
    trait Plus[A] {
      def plus(a1: A, a2: A): A
    }

    /**
     * This is truely ad-hoc in the sense that:
     * <ul>
     *  <li>1. we can provide separate function definitions for different types of A
     *  <li>2. we can provide function definitions to types (like Int) without access to its source code
     *  <li>3. the function definitions can be enabled or disabled in different scopes
     * </ul>
     */
    def plus[A: Plus](a1: A, a2: A): A = implicitly[Plus[A]].plus(a1, a2)
  }

  /** Starting from a simple function that adds up a list of Int */
  def sum(xs: List[Int]): Int = xs.foldLeft(0){_ + _}

  /**
   * More general.
   * It’s a type for which there exists a function mappend,
   * which produces another type in the same set; and also a function that produces a zero.
   */
  object IntMonoid {
    def mappend(a: Int, b: Int): Int = a + b
    def mzero: Int = 0
  }

  def sum1(xs: List[Int]) = xs.foldLeft(IntMonoid.mzero)(IntMonoid.mappend)

  /**
   * abstract on the type about Monoid, so we can define Monoid for any type A. So now IntMonoid is a monoid on Int
   */
  trait Monoid[A] {
    def mappend(a: A, b: A): A
    def mzero: A
  }

  object Monoid {
    implicit val intMonoid = IntMonoid1
    implicit val stringMonoid = StringMonoid1
  }

  object IntMonoid1 extends Monoid[Int] {
    override def mappend(a: Int, b: Int): Int = a + b
    override def mzero: Int = 0
  }
  object StringMonoid1 extends Monoid[String] {
    override def mappend(a: String, b: String): String = a + b
    override def mzero: String = ""
  }

  /** do is that sum take a List of Int and a monoid on Int to sum it */
  def sum2(xs: List[Int], m: Monoid[Int]): Int = xs.foldLeft(m.mzero){m.mappend}

  /** The final change we have to take is to make the Monoid implicit so we don’t have to specify it each time. */
  def sum3[A](xs: List[A])(implicit m: Monoid[A]): A = xs.foldLeft(m.mzero){m.mappend}
  // sum3: [A](xs: List[A])(implicit m: Monoid[A])A

  //but the implicit parameter is often written as a context bound, so
  def sum4[A: Monoid](xs: List[A]): A = {
    val m = implicitly[Monoid[A]]
    xs.foldLeft(m.mzero){m.mappend}
  }
  // sum4: [A](xs: List[A])(implicit evidence$1: Monoid[A])A

  object FoldLeftList {
    def foldLeft[A, B](xs: List[A], b: B, f:(B,A) => B) = xs.foldLeft(b)(f)
  }

  def sum5[A: Monoid](xs: List[A]): A = {
    val m = implicitly[Monoid[A]]
    FoldLeftList.foldLeft(xs, m.mzero, m.mappend)
  }

  trait FoldLeft[F[_]] {
    def foldLeft[A, B](xs: F[A], b: B, f:(B,A) => B): B
  }

  object FoldLeft {
    implicit val FoldLeftList: FoldLeft[List] = new FoldLeft[List] {
      override def foldLeft[A,B](xs: List[A], b: B, f:(B,A) => B): B = xs.foldLeft(b)(f)
    }
  }

  def sum6[M[_]: FoldLeft, A: Monoid](xs: M[A]): A = {
    val m = implicitly[Monoid[A]]
    val fl = implicitly[FoldLeft[M]]
    fl.foldLeft(xs, m.mzero, m.mappend)
  }
}

object Main extends App {
  println(day0.sum(List(1, 2, 3, 4)))
  println(day0.sum1(List(1, 2, 3, 4)))
  println(day0.sum2(List(1, 2, 3, 4), day0.IntMonoid1))

//  implicit val intMonoid = day0.IntMonoid1
//  implicit val stringMonoid = day0.StringMonoid1
  println(day0.sum3(List(1, 2, 3, 4)))
  println(day0.sum4(List(1, 2, 3, 4)))
  println(day0.sum4(List("a", "b", "c", "d")))
  println(day0.sum6(List("a", "b", "c", "d")))
}
