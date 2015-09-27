package com.domain

import com.config.MyPostgresDriver
import MyPostgresDriver.api._
import com.config.MyPostgresDriver
import slick.lifted.ProvenShape.proveShapeOf

case class Ola(id:Long, ola: String,isRemoved:Boolean)

trait MyTables{

  import slick.jdbc.{GetResult => GR}

  implicit def GetResultOla(implicit e0: GR[Long], e1: GR[String], e2: GR[Boolean], e3: GR[java.sql.Timestamp]): GR[Ola] = GR{
    prs => import prs._
      Ola.tupled((<<[Long], <<[String], <<[Boolean]))
  }
  class OlaTable(_tableTag: Tag) extends Table[Ola](_tableTag, Some("ola"), "Ola") {
    def * = (id, ola, isRemoved) <> (Ola.tupled, Ola.unapply)
    def ? = (Rep.Some(id), Rep.Some(ola), Rep.Some(isRemoved)).shaped.<>({r=>import r._; _1.map(_=> Ola.tupled((_1.get, _2.get, _3.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    val id: Rep[Long] = column[Long]("OlaId", O.PrimaryKey)
    val ola: Rep[String] = column[String]("Ola", O.Length(250,varying=true))
    val isRemoved: Rep[Boolean] = column[Boolean]("IsRemoved")
  }
  lazy val olaTable = new TableQuery(tag => new OlaTable(tag))
}

object MyTables extends {
  val driver = MyPostgresDriver
} with MyTables