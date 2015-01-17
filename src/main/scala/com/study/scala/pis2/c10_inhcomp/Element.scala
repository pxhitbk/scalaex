package com.study.scala.pis2.c10_inhcomp
abstract class Element {
	/*abstract method, return array of contents*/	
	def contents : Array[String]
	//height is empty paren method (againts height())
	def height : Int = contents.length	
	def width : Int = if (height == 0) 0 else contents(0).length
  def demo {
    println("Element's implementation invoke") 
  }
  
  /**
   * Putting one element above another means concatenating the two contents values of the elements.
   */
  def above(that : Element) = new ArrayElement(this.contents ++ that.contents)
  /**
   * To put two elements beside each other
   */
  def beside(that : Element) = {
    val contents = new Array[String](this.contents.length)
    for (i <- 0 until contents.length)
        contents(i) += this.contents(i) + that.contents(i)
    new ArrayElement(contents)    
  }
  
  def besideShortVersion(that : Element) = {
    for (
      (line1, line2) <- this.contents zip that.contents    
    ) yield line1 + line2
    
    //Note:
    //Array(1, 2, 3) zip Array("a", "b")
    //will evaluate to: Array((1, "a"), (2, "b")
  }
  
  override def toString = {contents mkString "\n"}
  
}

/**Factory object*/
object Element {
  def elem(contents : Array[String]) = new ArrayElement(contents)
  def elem(c : Char, w : Int, h : Int) = new UniformElement(c, w, h)
  def elem(line : String) = new LineElement(line)
}