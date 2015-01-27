package com.study.scala.pis2.c21_implicit

/**
 * @author Hung
 */
package object package_object_utils {
  /**Make extension methods on String*/
  implicit class StringImprovement(val s: String) {
    def increment = s.map { c => (c + 1).toChar }
    def decrement: String = s.map(c => (c - 1).toChar)
    def hideAll: String = s.replaceAll(".", "*")
    def plusOne = s.toInt + 1
    def asBoolean = s match {
      case "0" | "zero" | "" | " " => false
      case _ => true
    }
  }
}