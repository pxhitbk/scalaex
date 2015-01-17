package com.study.scala.pis2.c10_inhcomp
/**
 * <b>Polymorphism and dynamic binding</b> <br/>
 * define a new form of Element that has a given width and height 
 * and is filled everywhere with a given character
 */
class UniformElement(
  val ch :Char,
  override val width : Int,
  override val height : Int
) extends Element {
  private val line = ch.toString() * width
  def contents = Array.fill(height)(line)
}