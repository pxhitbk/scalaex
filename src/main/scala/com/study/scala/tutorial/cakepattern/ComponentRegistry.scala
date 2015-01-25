package com.study.scala.tutorial.cakepattern

/**
 * @author Hung
 */
object ComponentRegistry extends UserServiceComponent with UserRepositoryComponent {
  val userRepository = new UserRepository
  val userService = new UserService
}