package com.freetest

/**
 * @author g10-macmini
 */
object ComplexCollection {
  def main(args: Array[String]): Unit = {
    val m1 = Map("a" -> "Home", "b" -> "School", "c" -> "University")
		val m2 = Map("a" -> (1, "Tom"), "b" -> (2, "Jerry"), "c" -> (5, "Hugo"))
		val m3 = Map("a" -> ("ty", "oo"), "b" -> ("ta", "oa"), "c" -> ("tr", "oh"))
    
    val m12 = for {
        (k,v) <- m1
        (k1,v1) <- m2
        (k2,v2) <- m3
        if (k == k1 && k == k2)
      } yield (k, v, v1._1, v1._2, v2._1, v2._2)
      
      println(m12.mkString(", "))
  }
  
  def groupList() {
    val l = List(("aa", "Student", 1), 
        ("bb","Soccer", 2), ("ab","Soccer", 4), ("ad","Actor", 6), ("bb","Teacher", 5))
    val ls = l.groupBy((_._1)).map{kv => (kv._1, kv._2.map(_._2).mkString(" "), kv._2.map(_._3).sum)}
    println(ls.mkString(" | "))
  }
}