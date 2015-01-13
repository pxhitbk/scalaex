package com.study.scala.pis2.classnobj

import com.study.scala.pis2.classnobj.ChecksumAccumulator.calculate

object ClassNObject extends App {
  for (season <- List("winter", "summer", "autumn"))
    println(season + ": " + calculate(season))
}