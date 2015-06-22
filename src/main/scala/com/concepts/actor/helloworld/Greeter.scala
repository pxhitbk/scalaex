package com.concepts.actor.helloworld

import akka.actor.Actor
import akka.actor.Actor.Receive

/**
 * Created by g10-macmini on 6/22/15.
 */
object Greeter {
  case object Greet
  case object Done
}

class Greeter extends Actor {
  override def receive: Receive = {
    case Greeter.Greet =>
      println("Hello World!")
      sender() ! Greeter.Done
  }
}
