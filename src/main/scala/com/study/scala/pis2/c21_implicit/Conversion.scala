package com.study.scala.pis2.c21_implicit

import javax.swing.JButton
import java.awt.event.ActionListener
import java.awt.event.ActionEvent
import Conversion._

/**
 * @author Hung
 */
class Conversion {
  /**Traditional usage based on java, use inner class*/
  def introFromSwing() {
    val button = new JButton
    button.addActionListener(new ActionListener {
      def actionPerformed(event: ActionEvent) {
        println("pressed")
      }
    })
  }
  
  def introFromSwing1() {
    val button = new JButton
    button.addActionListener(
      function2ActionListener {
        (_: ActionEvent) => println("pressed") 
      }    
    )
  }

  def introFromSwing2() {
	  val button = new JButton
    /*The way this code works is that the compiler first tries to compile it as is,
     * but it sees a type error. 
     * Before giving up, it looks for an implicit conversion that can repair the problem. 
     * In this case, it finds function2ActionListener. 
     * It tries that conversion method, sees that it works, and moves on. */
			  button.addActionListener(
				 (_: ActionEvent) => println("pressed")    //<= type error if not has appropriate implicit method with type ActionEvent => Unit
			  )
        
        val a = (1 -> "")
  }
  
}

object Conversion {
  /**implicit function for */
  implicit def function2ActionListener(f: ActionEvent => Unit) =
    new ActionListener {
      def actionPerformed(event: ActionEvent) = f(event)
  }
  
  implicit def intToString(x: Int): String = {
    x + " ok"
  }
}