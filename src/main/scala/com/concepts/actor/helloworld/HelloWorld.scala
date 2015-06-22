package com.concepts.actor.helloworld

import akka.actor.{Props, Actor}
import akka.actor.Actor.Receive

/**
 * Created by g10-macmini on 6/22/15.
 */
class HelloWorld extends Actor{
  override def preStart(): Unit = {
    val greeter = context.actorOf(Props[Greeter], "asdasdasd")
    greeter ! Greeter.Greet
  }
  override def receive: Receive = {
    case Greeter.Done => context.stop(self)
  }
}
