package com.study.scala.pis2.c21_implicit

import javax.swing.JButton
import java.awt.event.ActionListener
import java.awt.event.ActionEvent
import Conversion._
import com.study.scala.pis2.c21_implicit.package_object_utils._
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

