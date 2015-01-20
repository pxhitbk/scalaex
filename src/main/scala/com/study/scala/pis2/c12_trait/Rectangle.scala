package com.study.scala.pis2.c12_trait

/**
 * @author Hung
 */

class Point(val x : Int, val y : Int) {
  override def toString() : String = "(" + x + ", " + y +")"
}

class Rectangle (val topLeft : Point, val bottomRight : Point) extends Rectangular {
//  def left = topLeft.x
//  def right = bottomRight.x
//  def width = right - left
  override def toString() : String = "top left : " + topLeft.toString() + ", bottom right: " + bottomRight.toString()
}

abstract class Component extends Rectangular {
//  def topLeft : Point
//  def bottomRight : Point
//  def left = topLeft.x
//  def right = bottomRight.x
//  def width = right - left
}

trait Rectangular {
  def topLeft : Point
  def bottomRight : Point
  def left = topLeft.x
  def right = bottomRight.x
  def width = right - left
}