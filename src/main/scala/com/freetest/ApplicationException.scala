package com.freetest

import scala.util.{Failure, Success, Try}

/**
 * Created by g10-macmini on 5/9/15.
 */
case class ApplicationException(code: String, message: String, cause: Throwable, gmoErrorCode: String = "") extends Exception(message, cause)

object Main extends App {
  val x = test(10)

  x match {
    case Success(value) =>
    case Failure(e) => e.printStackTrace()
  }

  def test(i: Int): Try[Int] = {
    val l = List(1, 2, 3, 4, 5, 6, 7)
    Try {
      l(i)
    } recover {
      case e: IndexOutOfBoundsException => throw ApplicationException("Error on list", e.getMessage, e)
      case e: Exception => throw ApplicationException("Error on list", e.getStackTrace.mkString("\n"), e)
    }

  }
}