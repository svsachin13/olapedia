package com.domain

import com.config.MyPostgresDriver
import MyPostgresDriver.api._
import com.config.MyPostgresDriver
import slick.lifted.ProvenShape.proveShapeOf

case class Hotel(id:Long, hotel: String,isRemoved:Boolean)

trait MyTables{

  import slick.jdbc.{GetResult => GR}

  implicit def GetResultHotel(implicit e0: GR[Long], e1: GR[String], e2: GR[Boolean], e3: GR[java.sql.Timestamp]): GR[Hotel] = GR{
    prs => import prs._
      Hotel.tupled((<<[Long], <<[String], <<[Boolean]))
  }
  class HotelTable(_tableTag: Tag) extends Table[Hotel](_tableTag, Some("hr"), "Hotel") {
    def * = (id, hotel, isRemoved) <> (Hotel.tupled, Hotel.unapply)
    def ? = (Rep.Some(id), Rep.Some(hotel), Rep.Some(isRemoved)).shaped.<>({r=>import r._; _1.map(_=> Hotel.tupled((_1.get, _2.get, _3.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    val id: Rep[Long] = column[Long]("HotelId", O.PrimaryKey)
    val hotel: Rep[String] = column[String]("Hotel", O.Length(250,varying=true))
    val isRemoved: Rep[Boolean] = column[Boolean]("IsRemoved")
  }
  lazy val hotelTable = new TableQuery(tag => new HotelTable(tag))
}

object MyTables extends {
  val driver = MyPostgresDriver
} with MyTables