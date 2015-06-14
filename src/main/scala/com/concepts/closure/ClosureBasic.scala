package com.concepts.closure

/**
 * Created by md101 on 6/15/15.
 */
object ClosureBasic extends App {

  /* Example from Wikipedia */
  startAt(4)(6)

  def startAt1(x: Int)(y: Int): Int = {
    x + y
  }

  def startAt(x: Int): Int => Int = {
    def increaseBy(y: Int): Int = {
      x + y
    }
    increaseBy
  }
}
