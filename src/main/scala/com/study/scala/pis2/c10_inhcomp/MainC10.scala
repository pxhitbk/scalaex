package com.study.scala.pis2.c10_inhcomp

object MainC10 {
  def main(args: Array[String]): Unit = {
    println("Chapter 10")    
    val ae2 : ArrayElement2 = new ArrayElement2(Array("Good way to define"))
    val printArr = (arr : Array[String]) => for (a <- arr) println(a)
    printArr(ae2.contents)
    var c = new Tiger(true, 10)
    c.age = 11
    println(c.toString())
    val ue = new UniformElement('z', 2, 3)
    printArr(ue.contents)
  }
}