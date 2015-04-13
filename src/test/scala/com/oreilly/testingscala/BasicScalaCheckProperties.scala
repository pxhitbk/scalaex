package com.oreilly.testingscala

import org.scalacheck.{Prop, Properties}

object BasicScalaCheckProperties extends Properties("Simple Math") {
  //Each property take a string describe purpose of the test
  property("Sum greater than its parts") = Prop.forAll{(x: Int, y: Int) => x + y > x && x + y > y}
  property("Sum is associative") = Prop.forAll{(x: Int, y: Int) => (x + y) == (y + x)}
}