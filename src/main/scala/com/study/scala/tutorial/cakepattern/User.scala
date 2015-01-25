package com.study.scala.tutorial.cakepattern

class User (username: String, password: String) {
  override def toString() = username + "," + password
}