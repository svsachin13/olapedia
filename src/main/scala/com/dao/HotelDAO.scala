package com.dao

import com.config.{DBFiles}
import DBFiles._
import com.config.MyPostgresDriver
import com.domain.MyTables.HotelTable
import com.domain._
import scala.concurrent.Future
import MyPostgresDriver.api._
import scala.concurrent.ExecutionContext.Implicits.global

class HotelDAO{
  def getHotels(): Future[Seq[Hotel]] ={
    val hotelQuery = TableQuery[HotelTable]
    for{
      res <- database.run(hotelQuery.result)
    }yield(res)
  }
}