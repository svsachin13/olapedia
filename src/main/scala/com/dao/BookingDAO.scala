package com.dao

import com.config.{DBFiles}
import DBFiles._
import com.config.MyPostgresDriver
import com.domain.MyTables.BookingTable
import com.domain._
import scala.concurrent.Future
import MyPostgresDriver.api._
import scala.concurrent.ExecutionContext.Implicits.global

class BookingDAO{
  def getBooking(): Future[Seq[Booking]] ={
    val bookingQuery = TableQuery[BookingTable]
    for{
      res <- database.run(bookingQuery.result)
    }yield(res)
  }

  def addBooking(data:List[Booking]): Future[Seq[Booking]] ={
    val bookingQuery = TableQuery[BookingTable]

    for{
      res <- database.run(bookingQuery returning bookingQuery.map(x => x) ++= data)
    }yield(res)
  }

  def updateBooking(id:Long,data:Booking) ={
    val bookingQuery = TableQuery[BookingTable]
    for{
      res <- database.run(bookingQuery.filter(_.id === id).update(data))
    }yield(res)
  }

  def deleteBooking(id:Long) ={
    val bookingQuery = TableQuery[BookingTable]
    for{
      res <- database.run(bookingQuery.filter(_.id === id).map(x => (x.isRemoved)).update((true)))
    }yield(res)
  }
}