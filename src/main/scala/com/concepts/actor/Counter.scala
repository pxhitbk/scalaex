package com.concepts.actor

import akka.actor.Actor
import akka.actor.Actor.Receive

/**
 * Created by md101 on 6/15/15.
 */
class Counter extends Actor{
  def counter(n: Int): Receive = {
    case "incr" => context.become(counter(n + 1))
    case "get" => sender() ! n
  }
  override def receive: Receive = counter(0)
}
