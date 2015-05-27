package com.freetest

import scala.annotation.tailrec

/**
 * Created by g10-macmini on 4/22/15.
 */
object Recursion extends App {

  def reverse_while(s: String): String = {
    if (s == null) return null
    val str = s.toCharArray
    var i = str.length - 1
    var reversed = ""
    while (i >= 0) {
      reversed += str(i)
      i -= 1
    }
    reversed
  }

  def reverse_rec(s: String): String = {
    println("do reverse: " + s)
    if (s == null) return null
    if (s.tail.isEmpty) return s
    reverse_rec(s.tail) + s.head
  }

  @tailrec
  def reverse_tail(s: String, r: String): String = {
    if (s == null) return null
    if (s.tail.isEmpty) s.head + r
    else reverse_tail(s.tail, s.head + r)
  }

  @tailrec
  def sum(n: List[Int]): Int = {
    if(n.isEmpty) 0
    else sum(n.tail)
  }

  println(reverse_rec("Hung"))
  println(reverse_tail("Hung", ""))
  println(reverse_while("Hung"))
}
