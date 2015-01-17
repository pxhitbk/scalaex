package com.study.scala.pis2.c10_inhcomp

class Tiger (
  override val dangerous : Boolean,
  var age : Int
) extends Cat {
  override def toString : String = {
    "Tiger is " + (if (dangerous) "" else " not") + "dangerous, age " + age 
  }
}