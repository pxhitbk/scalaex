package com.freetest

/** A monad is defined by three things:
  * a type constructor M;
  * a function return;
  * an operator (>>=) which is pronounced "bind".
 */
sealed trait Maybe[+A] {

  def flatMap[B](f: A => Maybe[B]): Maybe[B]

  def map[B](f: A => B): Maybe[B] = flatMap { a => Just(f(a)) }

  //Join
  /** <i>scala.Predef.<:<</i>, which reads, "An instance of A <:< B witnesses that A is a subtype of B.
    * Requiring an implicit argument of the type A <:< B encodes the generalized constraint A <: B. <br/>
    * So the implicit parameter in flatten provides a sort of identity mapping function
    * that maps type Maybe[A] onto type Maybe[Maybe[B]].
    * */
  def flatten[B](implicit asMaybeMaybe: Maybe[A] <:< Maybe[Maybe[B]]): Maybe[B] =
    asMaybeMaybe(this) flatMap identity

}

case class Just[+A](a: A) extends Maybe[A] {

  override def flatMap[B](f: A => Maybe[B]) = f(a)

}

case object MaybeNot extends Maybe[Nothing] {

  override def flatMap[B](f: (Nothing) => Maybe[B]): Maybe[B] = MaybeNot

}

object Person {

  val persons = List("P", "MP", "MMP", "FMP", "FP", "MFP", "FFP") map { Person(_) }

  private val mothers = Map(
    Person("P") -> Person("MP"),
    Person("MP") -> Person("MMP"),
    Person("FP") -> Person("MFP"),
    Person("P1") -> Person("MP1"),
    Person("MP1") -> Person("MMP1"),
    Person("FP1") -> Person("MFP1"))

  private val fathers = Map(
    Person("P") -> Person("FP"),
    Person("MP") -> Person("FMP"),
    Person("FP") -> Person("FFP"),
    Person("P1") -> Person("FP1"),
    Person("MP1") -> Person("FMP1"))

  def mother(p: Person): Maybe[Person] = relation(p, mothers)
  def father(p: Person): Maybe[Person] = relation(p, fathers)
  def masternalGrandfather(p: Person): Maybe[Person] = p.mother.flatMap(_.father)
  def masternalGrandfatherNoFlatMap(p: Person): Maybe[Person] = p.mother match {
    case Just(m) => m.father
    case MaybeNot => MaybeNot
  }
  def bothGrandfatherFlatMap(p: Person): Maybe[(Person, Person)] =
    p.mother.flatMap { m =>
      m.father.flatMap { fm =>
        p.father.flatMap { f =>
          f.father.flatMap {ff =>
            Just((fm, ff))
          }
        }
      }
  }

  def bothGrandfathersNoFlatMap(p: Person): Maybe[(Person, Person)] = {
    (p.mother, p.father) match {
      case (Just(m), Just(f)) =>
        (m.father, f.father) match {
          case (Just(fm), Just(ff)) => Just((fm, ff))
          case _ => MaybeNot
        }
      case _ => MaybeNot
    }
  }

  def bothGrandfathersForLoop(p: Person): Maybe[(Person, Person)] =
    for(
      m <- p.mother;
      fm <- m.father;
      f <- p.father;
      ff <- f.father)
      yield (fm, ff)

  def relation(p: Person, relationMap: Map[Person, Person]) = relationMap.get(p) match {
    case Some(m) => Just(m)
    case None => MaybeNot
  }
}

case class Person(name: String) {
  def mother: Maybe[Person] = Person.mother(this)
  def father: Maybe[Person] = Person.father(this)
  def masternalGrandFather: Maybe[Person] = Person.masternalGrandfather(this)
  def masternalGrandfatherNoFlatMap: Maybe[Person] = Person.masternalGrandfatherNoFlatMap(this)
  def bothGrandfatherFlatMap: Maybe[(Person, Person)] = Person.bothGrandfatherFlatMap(this)
  def bothGrandfathersNoFlatMap: Maybe[(Person, Person)] = Person.bothGrandfathersNoFlatMap(this)
  def bothGrandfathersForLoop(): Maybe[(Person, Person)] = Person.bothGrandfathersForLoop(this)
}

object Main {
  def main(args: Array[String]) {
    println(Person("P").masternalGrandFather)
    println(Person("P1").bothGrandfatherFlatMap)
    println(Person("P").bothGrandfathersForLoop())

    Person.persons foreach { p =>
      assert(Just(Just(p)).flatten == Just(p))
    }

    assert(Just(MaybeNot).flatten == MaybeNot)

    assert(MaybeNot.flatten == MaybeNot)
  }
}

