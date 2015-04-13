package com.oreilly.testingscala

import org.scalacheck.{Gen, Prop, Properties}
import org.scalacheck.Prop.{forAll, BooleanOperators}

/**
 * Created by hungpx on 4/13/15.
 */
object ArtistScalaCheckProperties extends Properties("Testing Artist Thoroughly"){
  property("middleNames") = {
    Prop.forAll {
      (firstName: String, middleName: Option[String], lastName: String) => {
        middleName match {
          case Some(m) =>
            val artist = new Artist(firstName, m, lastName)
            artist.fullName == firstName + " " + m + " " + lastName
          case _ =>
            val artist = new Artist(firstName, lastName)
            artist.fullName == firstName + " " + lastName
        }
      }
    }
  }

  property("middleNames with Gen") = {
    Prop.forAll(Gen.alphaStr, Gen.oneOf(Gen.alphaStr.sample, None), Gen.alphaStr) {
      (firstName: String, middleName: Option[String], lastName: String) => {
//        println(firstName, middleName, lastName)
        middleName match {
          case Some(m) =>
            val artist = new Artist(firstName, m, lastName)
            artist.fullName == firstName + " " + m + " " + lastName
          case _ =>
            val artist = new Artist(firstName, lastName)
            artist.fullName == firstName + " " + lastName
        }
      }
    }
  }

  property("Album Creation") = {
    Prop.forAll {
      (title: String, year: Int, firstName: String, lastName: String) => {
        (year > 1900 || year < 3000) ==> {
          println("year: " + year)
          val album = new Album(title, year, new Artist(firstName, lastName))
          album.year == year
          album.title == title
        }
      }
    }
  }

}
