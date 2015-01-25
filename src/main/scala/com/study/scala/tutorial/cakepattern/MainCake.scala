package com.study.scala.tutorial.cakepattern

object MainCake {
  def main(args: Array[String]): Unit = {
    val service = ComponentRegistry.userService
    service.authenticate("Hung", "pxh")
  }
}