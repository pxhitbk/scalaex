/*
 * Copyright (C) 2014 en-japan inc., All Rights Reserved.
 */
package com.freetest

import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.sql.Date
import java.util.Calendar
import org.joda.time.DateTime
import org.joda.time.Months


/**
 * DatetimeUtils
 */
object DatetimeUtils {
 
  /**
   * Get begin time of given month and year
   * @param month the month
   * @param year the year
   */
  def beginOfMonth(month: Int, year: Int): Timestamp = {
    val calendar: Calendar = Calendar.getInstance()
    calendar.set(year, month, 1, 0, 0, 0)
    calendar.set(Calendar.MILLISECOND, 0)
    new Timestamp(calendar.getTimeInMillis)
  }

  /**
   * recentYear
   */
  def recentYear(month: Int): Int = {
    if (month <= currentMonth) {
      Calendar.getInstance().get(Calendar.YEAR)
    } else {
      Calendar.getInstance().get(Calendar.YEAR) - 1
    }
  }

  /**
   * currentMonth
   */
  def currentMonth: Int = Calendar.getInstance().get(Calendar.MONTH)

  /**
   * currentYear
   */
  def currentYear: Int = Calendar.getInstance().get(Calendar.YEAR)

  /**
   * Next month of given month
   * @param month the month
   * @param year the year
   * @return the next month, year
   */
  def nextMonth(month: Int, year: Int): (Int, Int) = {
    if (month == Calendar.DECEMBER){
      (Calendar.JANUARY, year + 1)
    } else {
      (month + 1, year)
    }
  }

  /**
   * Get month of given time
   * @param time the time
   * @return the month
   */
  def getMonth(time: Timestamp): Int = {
    val calendar: Calendar = Calendar.getInstance()
    calendar.setTime(time)
    calendar.get(Calendar.MONTH)
  }

  /**
   * Get year of given time
   * @param time the time
   * @return the month
   */
  def getYear(time: Timestamp): Int = {
    val calendar: Calendar = Calendar.getInstance()
    calendar.setTime(time)
    calendar.get(Calendar.YEAR)
  }

  /**
   * Now
   */
  def now: Timestamp = {
    val calendar: Calendar = Calendar.getInstance()
    new Timestamp(calendar.getTimeInMillis)
  }

  /**
   * Truncate given time after add given day
   * @param time the time
   * @param day the addition time
   */
  def truncate(time: Timestamp, day: Int): Timestamp = {
    val calendar: Calendar = Calendar.getInstance()
    calendar.add(Calendar.DATE, day)
    calendar.set(Calendar.HOUR, 0)
    calendar.set(Calendar.MINUTE, 0)
    calendar.set(Calendar.SECOND, 0)
    calendar.set(Calendar.MILLISECOND, 0)
    new Timestamp(calendar.getTimeInMillis)
  }
  
  def quartersBetween(from: DateTime, to: DateTime) = {
    val fromQuarter = new Quarter(from)    
    val toQuarter = new Quarter(to)
    
    Math.abs(fromQuarter.quarter._2 - toQuarter.quarter._2) match {
      case 0 => fromQuarter.quarter._1 - toQuarter.quarter._1
      case a => a * 4 + toQuarter.quarter._1
    }
  }
}

class Quarter(val dateTime: DateTime) extends Ordered[Quarter] {
  val quarter = ((dateTime.getMonthOfYear - 1) / 3 + 1, dateTime.getYear())
  val beginDate = new DateTime(quarter._2, quarter._1 * 3 - 2, 1, 0, 0) 
  val endDate = beginDate.plusMonths(3).minusSeconds(1)
  
  override def compare(that: Quarter): Int = {
    val quarterNumberDiff = this.quarter._1 - that.quarter._1 
		(this.quarter._2 - that.quarter._2) match {
      case 0 => quarterNumberDiff
      case a => a
    } 
  }
  
  override def toString() = {
    "Q" + quarter + ":" +  beginDate.toString() + " - " + endDate.toString()
  }
}
