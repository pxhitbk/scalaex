package com.study.scala.pis2.c12_trait

object MainC12 {
  def main(args: Array[String]): Unit = {
//    rectangularExample
    stackableModifications
  }
  
  def firstExample() {
    val frog = new Frog
    frog.philosophize()
    
    val phil : Philosophical = frog
    println(phil.toString())
    phil.philosophize()
  }
  
  def rectangularExample {
    val rect = new Rectangle(new Point(1,1), new Point(10,10))
    println(rect.toString())
    println(rect.width)
  }
  
  def stackableModifications {
    val queue = new MyQueue
    queue.put(-1)
    queue.put(0)
    queue.put(1)
    queue.get()
    println(queue.toString())
  }
}