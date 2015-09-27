package com.domain

import java.sql.Timestamp

import com.config.MyPostgresDriver
import MyPostgresDriver.api._
import com.config.MyPostgresDriver
import slick.lifted.ProvenShape.proveShapeOf

case class Ola(id:Long, ola: String,isRemoved:Boolean)
case class Booking(id:Long, userName: String,startTime:Timestamp,endTime:Timestamp,isRemoved:Boolean,lattitude:Double,longitude:Double)


trait MyTables{

  import slick.jdbc.{GetResult => GR}

  implicit def GetResultOla(implicit e0: GR[Long], e1: GR[String], e2: GR[Boolean], e3: GR[java.sql.Timestamp]): GR[Ola] = GR{
    prs => import prs._
      Ola.tupled((<<[Long], <<[String], <<[Boolean]))
  }
  class OlaTable(_tableTag: Tag) extends Table[Ola](_tableTag, Some("ola"), "Ola") {
    def * = (id, ola, isRemoved) <> (Ola.tupled, Ola.unapply)
    def ? = (Rep.Some(id), Rep.Some(ola), Rep.Some(isRemoved)).shaped.<>({r=>import r._; _1.map(_=> Ola.tupled((_1.get, _2.get, _3.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    val id: Rep[Long] = column[Long]("OlaId", O.PrimaryKey, O.AutoInc)
    val ola: Rep[String] = column[String]("Ola", O.Length(250,varying=true))
    val isRemoved: Rep[Boolean] = column[Boolean]("IsRemoved")
  }
  lazy val olaTable = new TableQuery(tag => new OlaTable(tag))

  implicit def GetResultBooking(implicit e0: GR[Long], e1: GR[String], e2: GR[Boolean], e3: GR[java.sql.Timestamp]): GR[Booking] = GR{
    prs => import prs._
      Booking.tupled((<<[Long], <<[String], <<[Timestamp], <<[Timestamp], <<[Boolean], <<[Double], <<[Double]))
  }
  class BookingTable(_tableTag: Tag) extends Table[Booking](_tableTag, Some("ola"), "Booking") {
    def * = (id,userName,startTime,endTime,isRemoved,lattitude,longitude) <> (Booking.tupled, Booking.unapply)
    def ? = (Rep.Some(id), Rep.Some(userName), Rep.Some(startTime),Rep.Some(endTime), Rep.Some(isRemoved), Rep.Some(lattitude), Rep.Some(longitude)).shaped.<>({r=>import r._; _1.map(_=> Booking.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6.get, _7.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    val id: Rep[Long] = column[Long]("BookingId", O.PrimaryKey, O.AutoInc)
    val userName: Rep[String] = column[String]("UserName", O.Length(250,varying=true))
    val startTime: Rep[Timestamp] = column[Timestamp]("StartTime")
    val endTime: Rep[Timestamp] = column[Timestamp]("EndTime")
    val isRemoved: Rep[Boolean] = column[Boolean]("IsRemoved")
    val lattitude: Rep[Double] = column[Double]("Lattitude")
    val longitude: Rep[Double] = column[Double]("Longitude")
  }
  lazy val bookingTable = new TableQuery(tag => new BookingTable(tag))
}

object MyTables extends {
  val driver = MyPostgresDriver
} with MyTables