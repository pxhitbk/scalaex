package com.study.scala.pis2.c_16lists

/**
 * @author Hung
 */
object MainC16 {
  
  def main(args: Array[String]): Unit = {
//    listLiterals
    constructLists
  }
  
  def listLiterals {
    val fruit = List("Apple", "orange", "pears")
    val nums = List(1, 2, 3, 4, 5)
    val diag3 = List(
      List(1, 0, 0),
      List(0, 1, 0),
      List(0, 0, 1)
    )
    val empty = List()
  }
  
  def constructLists {
    val fruit = "Apple" :: "orange" :: "pears" :: Nil //same as above
    println(fruit.mkString(", "))
    
    println(fruit.head) //return first element of list 
    println(fruit.tail)  //return a list consisting of all elements except the first
    println(fruit.tail.head)
    println(fruit.last)
    println(fruit.init)
    
    println
    val nums = 3 :: 6 :: 4 :: 10 :: 5 :: List(2, 8)
    val diag3 = 
      (1 :: 0 :: 0 :: Nil) ::
      List(0, 1, 0) ::
      List(0, 0, 1) :: Nil

      val empty = Nil
      
      val sorted = isortPm(nums)
      println(sorted.mkString(", "))
  }
  
  def isort(xs : List[Int]) : List[Int] = {
    
    if (xs.isEmpty) {
      println("<isort> xs: Nil")
      Nil
    }
    else {
      println("<isort> " + xs.tail.mkString(", "))
      insert(xs.head, isort(xs.tail))
    }
  }
  
  private def insert(x : Int, xs : List[Int]): List[Int] = {
    println("<insert> x:" + x + "/xs=(" + xs.mkString(", ") + ")")
    if (xs.isEmpty || x <= xs.head) {
      println("\tappend x:" + x + " to xs=(" + xs.mkString(", ") + ")")
      x :: xs
    }
    else { 
      println("\tappend x:" + x + " to xs.tail =(" + xs.tail.mkString(", ") + ")")
      xs.head :: insert(x, xs.tail) }
  } 
  
   /**isort() re-implement with pattern matching*/
  def isortPm(xs : List[Int]): List[Int] = xs match {
    case List() => Nil
    case x :: xs1 => insertPm(x, isortPm(xs1))  
  }
  
  def insertPm(x: Int, xs: List[Int]): List[Int] = xs match {
    case List() => List(x)
    case y :: ys =>  
      if (x <= y) x :: xs
      else y :: insertPm(x, ys)
  }
}
