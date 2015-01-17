package com.study.scala.pis2.c10_inhcomp

/**Invoke super class constructor: <br \>
 * - Simply place the argument or arguments you want to pass in parentheses following the name of the superclass*/
class LineElement(s : String) extends ArrayElement(Array(s)) {
  override def width = s.length()
  override def height = 1
}