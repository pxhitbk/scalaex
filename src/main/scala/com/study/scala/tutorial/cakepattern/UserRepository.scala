package com.study.scala.tutorial.cakepattern

/**
 * @author Hung
 */
trait UserRepositoryComponent {
  val userRepository: UserRepository //abstract member field
  class UserRepository {
    def authenticate(user: User): User = { 
      println("authenticating user: " + user)
      user
     }
    def create(user: User) = println("creating user: " + user)
    def delete(user: User) = println("deleting user: " + user)
  }
}