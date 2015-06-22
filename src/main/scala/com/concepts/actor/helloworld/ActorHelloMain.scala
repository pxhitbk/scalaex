package com.concepts.actor.helloworld

import akka.Main

/**
 * Created by g10-macmini on 6/22/15.
 */
object ActorHelloMain {
  def main(args: Array[String]) {
    val hw = classOf[HelloWorld].getName
    println("invoke " + hw)
    Main.main(Array(hw))
  }
}
