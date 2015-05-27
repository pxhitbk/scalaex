package com.study.scala.fpis.c9_parsercombinators

/**
 * Created by md101 on 5/22/15.
 */
trait Parsers[ParserError, Parser[+_]] {
  self =>
  def run[A](p: Parser[A])(input: String): Either[ParserError, Parser[A]]
  def char(c: Char): Parser[Char]
  def orString(a: String, b: String): Parser[String]
  def or[A](a: Parser[A], b: Parser[A]): Parser[A]

  def listOfN[A](n: Int, p: Parser[A]): Parser[List[A]]

  implicit def string(s: String): Parser[String]
  implicit def operators[A](parser: Parser[A]): ParserOps[A]
  implicit def asStringParser[A](a: A)(implicit f:A => Parser[String]): ParserOps[String] = ParserOps(f(a))

  case class ParserOps[A](p: Parser[A]) {
    def |[B>:A](p2: Parser[B]): Parser[B] = self.or(p, p2)
    def or[B>:A](p2: Parser[B]): Parser[B] = self.or(p, p2)
  }
}
