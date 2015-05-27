package com.freetest

import scala.annotation.tailrec
import scala.collection.mutable.ListBuffer

trait Component {
  def name: String
  def hasChild(): Boolean
  def getChildren(): List[Component]
  def getLastChild(component: Component): Component = {
    var p = component
    while(p.hasChild()) {
      print("child of " + p.name + " is ")
      p = p.getChildren().head
      print(p.name + "\n")
    }
    p
  }

  @tailrec
  final def getLastChild1(component: Component): Component = {
    if(!component.hasChild()) component else getLastChild1(component.getChildren().head)
  }
}
case class Container(name: String) extends Component {
  val children = new ListBuffer[Component]

  def addChild(component: Component) = children += component
  def hasChild(): Boolean = !children.isEmpty
  def getChildren(): List[Component] = children.toList
}
case class Leaf(name: String) extends Component {
  override def hasChild(): Boolean = false
  override def getChildren(): List[Component] = Nil
}

object RecusionMain extends App {
  val first = new Container("first")
  val first1 = new Container("first1")
  val first2 = new Container("first2")
  val first3 = new Container("first3")
  val first4 = new Container("first4")
  val first5 = new Container("first5")
  val first6 = new Container("first6")
  val first7 = new Leaf("first7")

  first.addChild(first1)
  first1.addChild(first3)
  first2.addChild(first5)
  first3.addChild(first4)
  first4.addChild(first7)
  first5.addChild(first6)

  val l = first.getLastChild(first1)
  val l1 = first.getLastChild1(first1)
  println("Last child is " + l.name)
  println("Last child1 is " + l1.name)
}

