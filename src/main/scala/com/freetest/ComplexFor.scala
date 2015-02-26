package com.freetest

import java.util.Calendar
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.TimeZone
import java.sql.Date
import org.joda.time.Days
import org.joda.time.DateTime
import org.joda.time.Months
import com.enjapan.careercard.utils.DatetimeUtils

/**
 * @author g10-macmini
 */
object ComplexFor {
  def main(args: Array[String]): Unit = {
    //        testExpiredDeposit()
//        testDateTimeRange()
//    testGroupPeriod()
    
    
//    var m = scala.collection.mutable.Map(new DateTime(2015,11,22, 0, 0) -> 1)
    
    val jd = new DateTime
//    println(jd)
//    println(new Date(jd.getMillis))
        
    val c = Calendar.getInstance
    c.set(2015, 1, 1)
    val d1 = new DateTime(c.getTime)
    c.set(2015, 1, 28)
    val d2 = new DateTime(c.getTime)
    println(d2.getMonthOfYear)
//    println(d1.monthOfYear().compareTo(d2.monthOfYear().getDateTime))
        
//    d.monthOfYear().compareTo(new DateTime(i.date).monthOfYear().getDateTime)
    
//    println(d2.dayOfMonth().withMaximumValue())
    
    val filterMonth = Calendar.JANUARY
    val recentYear = DatetimeUtils.recentYear(filterMonth)
    val dt = new DateTime(recentYear, filterMonth + 1, 1, 0, 0)
//    println(dt)
    val from = dt.dayOfMonth().withMinimumValue()
    val to = dt.dayOfMonth().withMaximumValue()
//    println("FROM: " + from + " TO: " + to)
    
    val endDate = new DateTime(System.currentTimeMillis()).dayOfMonth().withMaximumValue()
    val startDate = endDate.minusMonths(11).dayOfMonth().withMinimumValue()
    val f = new Timestamp(startDate.getMillis)
    val t = new Timestamp(endDate.getMillis)
//    println("FROM: " + f + " TO: " + t)
    
    val dt1 = new DateTime("2014-02-01T00:21:00.000+07:00")
    val dt2 = new DateTime(2015,2,6,0,0)
    val dt3 = new DateTime("2015-02-01T00:01:00.000+07:00")
    println(dt1.monthOfYear().getDateTime.getYear)
    println(dt3.monthOfYear().getDateTime.getYear)
    
    println(">>"+dt1.monthOfYear().compareTo(dt3.monthOfYear().getDateTime) )
    println(">>"+Months.monthsBetween(dt1, dt3).getMonths )
  }

  def testGroupPeriod() {
    val c = Calendar.getInstance
    c.set(2014, 1, 1)
    val startDate = new DateTime(c.getTime)
    c.set(2015, 2, 1)
    val endDate = new DateTime(c.getTime)
//    val numOfDays = Days.daysBetween(startDate, endDate).getDays
    val numOfMonths = Months.monthsBetween(startDate, endDate).getMonths
//    val dateRanges = for (f <- 0 to numOfDays) yield startDate.plusDays(f)
    val monthRanges = for (f <- 0 to numOfMonths) yield startDate.plusMonths(f)
    
    val imp = getDailyImpressionRows()

    var ret = scala.collection.mutable.ListBuffer.empty[(DateTime, Int)]
    for(m <- monthRanges) {
      var monthCount = 0
//      var count = 0
//      val firstDayOfMonth = m.dayOfMonth().withMinimumValue()
//      val lastDayOfMoth = m.dayOfMonth().withMaximumValue()
//      val numOfDays = Days.daysBetween(firstDayOfMonth, lastDayOfMoth).getDays
//      val dateRanges = for (f <- 0 to numOfDays) yield firstDayOfMonth.plusDays(f)
//      for (d <- dateRanges) {
//        if (d.monthOfYear().compareTo(m.monthOfYear().getDateTime) == 0)
        for (i <- imp) {
//          if (d.withTimeAtStartOfDay().isEqual(new DateTime(i.date).withTimeAtStartOfDay())) {
          	if (m.monthOfYear().compareTo(new DateTime(i.date).monthOfYear().getDateTime) == 0) {
//              println(m.monthOfYear().getDateTime + ": " + i.count)
//              println(d + ": " + i.count)
              monthCount += i.count
            }
        }
//      }
//      monthCount += monthCount
      ret += ((m, monthCount))
    }

    println(ret.mkString(", "))
  }

  def testDateTimeRange() {
    val c = Calendar.getInstance
    c.set(2015, 1, 10)
    val startDate = new DateTime(c.getTime)
    c.set(2015, 2, 10)
    val endDate = new DateTime(c.getTime)
    val numOfDays = Days.daysBetween(startDate, endDate).getDays
    val numOfMonths = Months.monthsBetween(startDate, endDate).getMonths
    val dateRanges = for (f <- 0 to numOfDays) yield startDate.plusDays(f)
    val monthRanges = for (f <- 0 to numOfMonths) yield startDate.plusMonths(f)

    println(dateRanges.mkString(", "))
    println(monthRanges.mkString(", "))
  }

  def testExpiredDeposit() {
    val v1 = List((1L, 100.0), (2L, 100.001))
    val v2 = List((2L, 90.0))
    val v3 = List((2L, 10.0))

    //    val v1 = List((1, 150.0), (2, 100.0), (4, 80.0), (5, 120.0), (7, 140.0), (8, 300.0), (11, 220.0))  
    //    val v2 = List((1, 100.0), (2, 60.0), (4, 20.0), (5, 120.0), (8, 100.0), (11, 200.0))  
    //    val v3 = List((1, 50.0),  (4, 10.0), (5, 120.0), (8, 50.0), (11, 20.0))  

    //get all expired deposit in history (custId, amount)
    //
    val v1Id = collection.mutable.Map() ++ v1.toMap
    val v2Id = v2.toMap
    val v3Id = v3.toMap

    val diff = for {
      (k, v) <- v1Id
      consume = v2Id.get(k).getOrElse(0.0).asInstanceOf[Double]
      expired = v3Id.get(k).getOrElse(0.0).asInstanceOf[Double]
    } yield ((k, v - consume - expired))

    //    for ((k,v) <- v1Id) {
    //      if (v2Id.contains(k)) {
    //        println("update v2: " + v1Id(k) + " - " + v2Id(k))
    //        val mn = v1Id(k) - v2Id(k)
    //        v1Id.update(k, mn)
    //      }
    //      if (v3Id.contains(k)) {
    //        println("update v3: " + v1Id(k) + " - " + v3Id(k))
    //        v1Id.update(k, v1Id(k) - v3Id(k))
    //      }
    //    }

    println("Expired to check: " + v1Id)

    //    val diff1 = for {
    //      (cidDep, amDep) <- v1
    //      (cidCons, amCons) <- v2
    //      (cidExp, amExp) <- v3
    //      if cidDep == cidCons && cidDep == cidExp
    //    } yield (cidDep, amDep - amCons - amExp)
    //    println("diff: " + diff.mkString(", "))

    val newexp = diff.filter {
      case (_, a) => a > 0.0
    }.
      map(dif => (dif._1, dif._2))

    //    val newExpiredDeposits = diff1.filter {
    //      case (_, a) => a > 0
    //    }.
    //      map(dif => (dif._1, dif._2))

    println("nexexp: " + newexp.mkString(", "))
  }

  def now(): Timestamp = {
    val sdf =
      new SimpleDateFormat("EEE, MMM d, yyyy hh:mm:ss a z");

    // Give it to me in GMT time.
    //    sdf.setTimeZone(TimeZone.getTimeZone("GMT+9"));

    val calendar: Calendar = Calendar.getInstance()
    println(sdf.format(calendar.getTime))
    new Timestamp(calendar.getTimeInMillis)
  }

  def previousYear() {
    val calendar = Calendar.getInstance()
    val current = calendar.getTime
    calendar.add(Calendar.YEAR, -1)
    val previous = calendar.getTime
    println(current)
    println(previous)
  }

  def joinList() {
    val l1 = List((1, "a"), (3, "b"))
    val l2 = List((3, "a"), (4, "c"))

    val l = l1 ::: l2
    val m = Map[String, Int]()
    (m /: l) {
      case (map, (i, s)) => { map.updated(s, i + (map.get(s) getOrElse 0)) }
    }.toList // Note: Tuples are reversed.

    println(m.mkString(", "))
  }

  def getDailyImpressionRows(): List[DailyImpressionRow] = {
    val c = Calendar.getInstance()
    c.set(2014, 6, 1)
    val r1 = DailyImpressionRow(1, "a", new Date(c.getTime.getTime), 1)
    c.set(2014, 7, 1)
    val r2 = DailyImpressionRow(2, "b", new Date(c.getTime.getTime), 3)
    c.set(2014, 8, 3)
    val r3 = DailyImpressionRow(3, "a", new Date(c.getTime.getTime), 3)
    c.set(2014, 8, 4)
    val r4 = DailyImpressionRow(4, "d", new Date(c.getTime.getTime), 2)
    c.set(2014, 8, 20)
    val r5 = DailyImpressionRow(5, "e", new Date(c.getTime.getTime), 5)
    c.set(2014, 9, 1)
    val r6 = DailyImpressionRow(6, "a", new Date(c.getTime.getTime), 5)
    c.set(2014, 9, 11)
    val r7 = DailyImpressionRow(7, "d", new Date(c.getTime.getTime), 4)
    c.set(2014, 10, 3)
    val r9 = DailyImpressionRow(8, "e", new Date(c.getTime.getTime), 2)
    c.set(2014, 12, 4)
    val r10 = DailyImpressionRow(9, "f", new Date(c.getTime.getTime), 3)
    c.set(2015, 1, 1)
    val r11 = DailyImpressionRow(10, "c", new Date(c.getTime.getTime), 6)
    c.set(2015, 1, 3)
    val r12 = DailyImpressionRow(11, "a", new Date(c.getTime.getTime), 1)
    c.set(2015, 1, 5)
    val r13 = DailyImpressionRow(12, "e", new Date(c.getTime.getTime), 2)
    c.set(2015, 2, 10)
    val r14 = DailyImpressionRow(13, "b", new Date(c.getTime.getTime), 7)
    c.set(2015, 2, 20)
    val r15 = DailyImpressionRow(14, "b", new Date(c.getTime.getTime), 3)
    List(r1, r2, r3, r4, r5, r6, r7)
  }
}

case class DailyImpressionRow(id: Long, jobPostingId: String, date: Date, count: Int) 

