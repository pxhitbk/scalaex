package com.oreilly.scalainaction.closure

/**
 * You could use any method as an infix or postfix operator, and closures in Scala
 * can be passed as “pass by name” arguments to other functions (see the next listing).
 * These features make it easier for developers to define new constructs.
 * Let’s create a new looping construct called loopTill, which is similar to the while loop in the following listing.
 */
object LoopTill extends App {
  /**
   * As long as condition is true, the closure body will be execute
   * @param cond condition
   * @param body the closure
   * @return
   */
  def loopTill(cond: => Boolean)(body: => Unit): Unit = {
    if (cond) {
      body
      loopTill(cond)(body)
    }
  }

  var i = 10 // free variable was bound to loopTill function block (lexical scope)
  loopTill(i > 0) {
    println(i)
    i -= 1
  }

  val rs = List(1,2,3).par.map(n => n * n)
}

/*
  DEFINITION
  Closure is a first-class function
  with free variables that are bound in the lexical environment.
  In the loopTill example, the free variable is i.
  Even though it’s defined outside the closure, you could still use it inside.
  The second parameter in the loopTill example is a closure,
  and in Scala that’s represented as an object of type scala.Function0.
 */
