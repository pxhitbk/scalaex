package com.study.scala.pis2.c21_implicit

import javax.swing.JButton
import java.awt.event.ActionListener
import java.awt.event.ActionEvent
import Conversion._
import package_object_utils._
import JoesPrefs._

object MainC21 {
  def main(args: Array[String]): Unit = {
    println("HAL".increment)
    println("10".plusOne)
    
    printString(4)
    
    //implicit parameters
    val bobsPrompt = new PreferredPrompt("relax> ")
    Greeter.greet("Hung")(bobsPrompt)
    
    Greeter.greet("Hung")
    Greeter.greet1("Hung")
    Greeter.greet1("Hung")(prompt, drunk)
    
    //
    val m: Person = MaxList1.maxListUpBound(List(new Person(1), new Person(3), new Person(2)))
    println(m.age)
    val mi = MaxList1.maxListImpParm(List(1, 2, 4, 3))
    println(mi)
    
    val x: String => Int = _.toInt + 1
    val y = x("4")
    println(y)
  }
  
  def introFromSwing2() {
    val button = new JButton
        button.addActionListener(
         (_: ActionEvent) => println("pressed")    //need to import implicit method
        )
  }
  
  def printString(x: String) {
    println(x)
  } 
}

