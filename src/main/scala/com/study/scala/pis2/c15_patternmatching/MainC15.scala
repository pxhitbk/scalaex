package com.study.scala.pis2.c15_patternmatching

import math.{E, Pi}
import scala.collection.immutable.Map

object MainC15 {
  def main(args: Array[String]): Unit = {
    val v = Var("x") //doesn't need initial by 'new Var("")' since Var is case class
    
    val op = BinOp("+", Number(1.0), v)
    println(op.right == Var("x")) //equal was implement automatically
    
    val div = op.copy(operator = "-") //copy object with the change that specify by named parameter
    println(div.operator)
    
    simplifyTop(UnOp("-", UnOp("-", op)))
    simplifyTop(BinOp("+", UnOp("-", op), Number(0)))
    
    println(constantPattern("hello"))
    
//    println(E)
//    println(Pi)
    
    tuplePatterns(List(0), "what", "the")
    
    println(typePattern("Empty String"))
    
    println(variableBinding(UnOp("abs", UnOp("abs", v))))
    
    val myTuple = (123, "abc")
    val (number, string) = myTuple
    
    val withDefault: Option[Int] => Int = {
      case Some(x) => x
      case None => 0
    }
    
    println("withDefault test value: " + withDefault(Some(20)))  
    
    val second: List[Int] => Int = {
      case x :: y :: _ => y
      //get MatchError pass a list less than 2 elements. See second1 function to solve
    }
    
    val second1: PartialFunction[List[Int], Int] = {
      case x :: y :: _ => y
      
      //alternative case above will be translated to following partial function value
      /*
       new PartialFunction[List[Int], Int] {
        def apply(xs: List[Int]) = xs match {
          case x :: y :: _ => y
        }
        def isDefinedAt(xs: List[Int]) = xs match {
          case x :: y :: _ => true
          case _ => false
        }
      }
      */
    }
    
    println(second1.isDefinedAt(List(1,2)))
    
    val num = 0 to 10;
    val num1 = num.map { x => x + 1 }
    println(num1.mkString(", "))
    
    withForExpression()
  }
  
  def simplifyTop(expr: Expr) : Expr = expr match {
    case UnOp("-", UnOp("-", e)) => { //Double negation
      println("case 1 " + e.getClass.getName)
      e
    }
    case BinOp("+", e, Number(0)) => { //Adding zero
      println("case 2 " + e.getClass.getName)
      e
    }
    case BinOp("*", e, Number(1)) => { //Multiplying by one
      println("case 3 " + e.getClass.getName)
      e
    }
    case _ => {  //Wildcard pattern
      println("Default: " +expr.getClass.getName)
      expr
    }
  }
  
  def constantPattern(x : Any) = x match {
    case 5 => "five"
    case true => "truth"
    case "hello" => "hi!"
    case Nil => "The empty list"
    case _ => "something else"
  }
  
  def sequencePatterns(x : Any) = x match {
    case List(0,_,_) => println("found list")
    case _ =>
  }
  
  def tuplePatterns(x : Any) = x match {
    case (a, b, c) => println("match " + a + b + c)
    case r => println("wonder: " + r.getClass().getName)
//    case _ => println("Any") //unreachable because of r
  }  
  
  def typePattern(x : Any) = x match {
    case s: String => s.size
    case m: Map[_, _] => m.size
  }  

  def variableBinding(expr : Expr) = expr match {
    case UnOp("abs", e  @ UnOp("abs", _)) => e.arg //declare 'e' as variable of UnOp("abs",_)
    case _ => "mismatch"
  }  
  
  /**
   * Replaces sum expressions with two identical operands 
   * e + e by multiplications of two e*2.<br/><br/>
   * => Apply <b>Pattern guard</b>: comes after a pattern and starts with an <i>if</i>.
   */
  def simplifyAdd(e: Expr) = e match {
    //case BinOp("+", x, x) => BinOp("*", x, Number(2))  //invalid declaration for second x
    case BinOp("+", x, y) if x == y => BinOp("*", x, Number(2)) //'if x == y' is pattern guard
    case _ => e
  }
  
  def simplifyAll(expr : Expr): Expr = expr match {
    case UnOp("-", UnOp("-", e)) =>
      simplifyAll(e)
    case BinOp("+", e, Number(0)) =>
      simplifyAll(e)
    case BinOp("*", e, Number(1)) =>
      simplifyAll(e) // ‘1’ is a neutral element for "*"
    case UnOp(op, e) =>
      UnOp(op, simplifyAll(e))
    case BinOp(op, l, r) =>
      BinOp(op, simplifyAll(l), simplifyAll(r))
    case _ => expr
  } 
  
  /**
   * <b>Option</b> type
   */
  def show(x: Option[String]) = x match {
    case Some(s) => s
    case None => "?"
  }
  
  def withForExpression() {
    val capitals = Map("vietnam" -> "Hanoi", "us" -> "New York")
    //this always match
    for ((country, city) <- capitals)
      println("The capital of " + country + " is " + city)
      
      val results = List(Some("apple"), None, Some("orange"), Some(1))
      //match for Some type
      for (Some(fruit) <- results)
        println(fruit)
  }
}