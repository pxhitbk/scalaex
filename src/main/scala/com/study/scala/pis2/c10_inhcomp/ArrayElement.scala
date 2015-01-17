package com.study.scala.pis2.c10_inhcomp
class ArrayElement(conts : Array[String]) extends Element {
	def contents : Array[String] = conts
	//val contents : Array[String] = conts
  final override def demo() {
   println("ArrayElement's implementation invoke") 
  }
}
