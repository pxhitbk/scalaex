package com.freetest

import scala.concurrent.Future
import scala.util.{Failure, Success, Try}
import scala.concurrent.ExecutionContext.Implicits.global

/**
 * Created by g10-macmini on 5/26/15.
 */
object FutureTest {
  val url = "http://acb.vn"
  def main(args: Array[String]): Unit = {
    val d = doBusiness()
    d map {
      case _ =>
        doBusinessOnSuccess()
        println("DONE")
    }
    d.onFailure {
      case e: Exception =>
        doBusinessOnFailure()
        println("ERROR " + e.getMessage)
    }

//    d.andThen {
//      case Success(a) => doBusiness1()
//      case Failure(e) => println("Error " + e.getMessage)
//    }
  }

  def doBusinessOnFailure(): Unit = {
    println("Process Business when fail")
  }

  def doBusinessOnSuccess(): Future[Unit] = {
    println("Process Business when success")
    val rt = retrieveSth(false)
    rt.onFailure {
      case e: Exception => println("Unable to retrieve. Cause " + e.getMessage)
    }
    rt.map {
      case r => println("Retrieve " + r)
//        Future.successful(r)
    }
  }

  def retrieveSth(input: Boolean): Future[String] = {
    if (input) Future{println("Try to retrieve sth"); "Good"}
    else Future.failed(new IllegalArgumentException("NotFound - 404"))
  }

  def doBusiness(): Future[Unit] = {
    println("Begin do business")
    val rs = retrieveSth(url)
//    rs.map { sc =>
//      println("We got " + sc)
//    } recover {
//      case e: Exception => {
//        println("error, do update.")
//        updateSth(Some("new thing"))
//        throw e
//      }
//    }

//    rs onSuccess {
//      case sc => println("We got " + sc)
//    }
//
//    rs onFailure {
//      case e: Exception =>
//        println("error, do update.")
//        updateSth(Some("new thing"))
//        throw e
//    }

    for {
      _ <- rs
      _ <- updateSth(Some("one new thing"))
    } yield ()
  }

  def retrieveSth(url: String): Future[String] = {
    if(url.contains("http")) {
      Thread.sleep(2000)
      Future.successful{
        println("Try to retrieve sth from " + url)
        "Good"
      }
    }
    else {
//      Future.failed((new IllegalArgumentException("Wrong url")))
      Future.failed(new IllegalArgumentException("Wrong url"))
    }
  }

  def updateSth(toUpdate: Option[String]): Future[Unit] = {
    if(toUpdate.isDefined)
      Future.successful(println("I now update " + toUpdate.get))
    else {
      Future.failed(new IllegalArgumentException("Missing parameter"))
    }
  }

}

