package com.framework

import org.slf4j.LoggerFactory

/**
 * Created by g10-macmini on 5/7/15.
 */
object LoggbackMain extends App{
//  val sample = new LogbackSample
//  val logger = LoggerFactory.getLogger("application")
//
//  sample.doSomeLog()
//  try {
//    val l = List()
//    l(1)
//  } catch {
//    case e => logger.warn(s"Error on ${this.getClass.getName}", e)
//  }
  val t:List[Option[Int]] = List(Some(1), Some(2), Some(3))
  var counter = 0
  def getNextT(): Option[Int] = {
    try {
      counter = counter + 1
      for {at <- t(counter - 1)
      } yield at
    } catch {
      case e: Exception => None
    }
  }

  var i: Option[Int] = None
  do {
    i = getNextT()
    println(i)
  } while (i.isDefined)

}
