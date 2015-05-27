package com.freetest

import scala.TraversableOnce
import scala.collection.generic.CanBuildFrom
import scala.collection._
import scala.collection.immutable.IndexedSeq

/**
 * Created by md101 on 5/17/15.
 */
object ScalaHigherKinded {
  //Generic in java was introduced to avoid class cast exception
  //Proper type: like java String, doesn't take type argument
  //Java allows only the abstraction over proper types, , Scala considers also generic types as first-class types.
  def main (args: Array[String]) {
    val l: SList[Int] = new SList
    val f = l.filter(_ => true)
    val transform = l.map[String](_ => "")

    val s: SSeq[String] = new SSeq[String]
    val sf = s.filter(_ => true)

    val l1 = List(1, 2, 3)
    val f1 = l1.filter(_ > 0)

  }
}

trait SIterable[E, C[E]]{
  def filter(p : E => Boolean): C[E] = ???
  def map[Y](p : E => Y): C[Y] = ???
}
//
//trait SIterableMap[E, C[[A][B]]] {
//
//}

class SList[A] extends SIterable[A, SList] {
}

class SSeq[K] extends SIterable[K, SSeq] {
  def size = 0
}
