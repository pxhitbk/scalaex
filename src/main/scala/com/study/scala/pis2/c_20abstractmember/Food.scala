package com.study.scala.pis2.c_20abstractmember
object Animals {
  class Food
  class Grass extends Food
  class Fish extends Food
  
  abstract class Animal {
    /**The type has an upper bound, Food, which is expressed by the “<: Food” clause*/
    type SuitalbeFood <: Food //ensure animal can eat only food that suitable
    def eat(food: SuitalbeFood)
  }
  class Cow extends Animal {
    type SuitalbeFood = Grass
    override def eat(food: SuitalbeFood) {}
  }
}