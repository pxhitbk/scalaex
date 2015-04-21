package com.study.scala.tutorial.scalaz

/**
 * Created by md101 on 4/19/15.
 */
package typeclass {
trait CanTruthy[A] { //self =>
  def truthys(a: A): Boolean
}

object CanTruthy {
  def apply[A](implicit  ev: CanTruthy[A]): CanTruthy[A] = ev
  def truthys[A](f: A => Boolean): CanTruthy[A] = new CanTruthy[A] {
    override def truthys(a: A): Boolean = f(a)
  }
}

trait CanTruthyOps[A] {
  def self: A
  implicit def F: CanTruthy[A]
  def truthy = F.truthys(self)
}

object ToCanIsTruthyOps {
  implicit def toCanIsTruthyOps[A] (v: A)(implicit av: CanTruthy[A]): CanTruthyOps[A] = new CanTruthyOps[A] {
    override def self: A = v
    override implicit def F: CanTruthy[A] = av
  }
}

  object Main extends App {
    import ToCanIsTruthyOps._

    implicit val intCanTruthy: CanTruthy[Int] = CanTruthy.truthys({
      case 0 => false
      case _ => true
    })
    implicit def listCanTruthy[A]: CanTruthy[List[A]] = CanTruthy.truthys({
      case Nil => false
      case _ => true
    })
    implicit val nilCanTruthy: CanTruthy[scala.collection.immutable.Nil.type] = CanTruthy.truthys(_ => false)
    implicit val booleanCanTruthy: CanTruthy[Boolean] = CanTruthy.truthys(identity)

    println(0.truthy)
    println(List("").truthy)
    println(Nil.truthy)

    def truthyIf[A: CanTruthy, B, C](cond: A)(ifyes: B)(ifno: C) =
      if (cond.truthy) ifyes
      else ifno

    val r = truthyIf (Nil) {"YEAH!"} {"NO!"}
    println(r)
  }
//$read.ToCanIsTruthyOps.toCanIsTruthyOps(10)($read.intCanTruthy).truthy
}
