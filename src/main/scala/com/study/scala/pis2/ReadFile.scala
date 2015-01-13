package com.study.scala.pis2

import scala.io.Source
object ReadFile extends App {
  if (args.length > 0 ) {
    for (line <- Source.fromFile(args(0)).getLines()) {
      println(line.length + " " + line)
    }  
  } else {
    println("Missing file.")
  }
}
