package com.study.scala.pis2.c12_trait

import scala.collection.mutable.ArrayBuffer

/**
 * @author Hung
 */
/**
 * IntQueue has a put method that adds new integers to the queue 
 * and a get method that removes and returns them.
 */
abstract class IntQueue {
  def get() : Int
  def put(x : Int)
}

trait Doubling extends IntQueue {
  abstract override def put(x : Int) {super.put(2 * x)}
}

trait Incrementing extends IntQueue {
  abstract override def put(x: Int) { super.put(x + 1) }
}

trait Filtering extends IntQueue {
  abstract override def put(x: Int) {
    if (x >= 0) super.put(x)
  }
}

class BasicIntQueue extends IntQueue {
  private val buf = new ArrayBuffer[Int]
  
  def get() = buf.remove(0)
  def put(x : Int) {buf += x}
  
  override def toString() : String = {buf.mkString(", ")}
}

/**
 * class MyQueue, which extends BasicIntQueue and mixes in Doubling
 */
class MyQueue extends BasicIntQueue with Incrementing with Doubling