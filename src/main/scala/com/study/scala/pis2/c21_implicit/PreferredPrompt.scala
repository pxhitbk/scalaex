package com.study.scala.pis2.c21_implicit

/**
 * @author Hung
 */
class PreferredPrompt(val preference: String)
class PreferredDrink(val preference: String)

object Greeter {
  def greet(name: String)(implicit prompt: PreferredPrompt) {
    println("Welcome, "+ name +". The system is ready.")
    println(prompt.preference)
  }

  /*Note that the implicit keyword applies to an entire parameter list, not to individual parameters.*/
  def greet1(name: String)(implicit prompt: PreferredPrompt, drink: PreferredDrink) {
	  println("Welcome, "+ name +". The system is ready.")
    print("But while you work, ")
    println("why not enjoy a cup of "+ drink.preference +"?")
    println(prompt.preference)
  }
}

object JoesPrefs {
  implicit val prompt = new PreferredPrompt("Yes, master> ")
  implicit val drink = new PreferredDrink("tea")
  val drunk = new PreferredDrink("Wine")
}
