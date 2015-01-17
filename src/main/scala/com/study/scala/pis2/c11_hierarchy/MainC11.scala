package com.study.scala.pis2.c11_hierarchy

object MainC11 {
  def main(args: Array[String]): Unit = {
    val x = "abcd".substring(2)
    val y = "abcd".substring(2)
    println(x.equals(y))
    println(x == y) //'==' same like equal in java
    println(x eq y) //'eq' same like == in java, reference equality
    if (!(x eq y))
      error("x and y doesn't has same reference")
  }
  //Use 'Nothing', bottom type 
  def error(message : String) : Nothing = {
    throw new RuntimeException(message)
  }
}