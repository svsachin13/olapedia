package com.dao

import com.config.{DBFiles}
import DBFiles._
import com.config.MyPostgresDriver
import com.domain.MyTables.OlaTable
import com.domain._
import scala.concurrent.Future
import MyPostgresDriver.api._
import scala.concurrent.ExecutionContext.Implicits.global

class OlaDAO{
  def getOla(): Future[Seq[Ola]] ={
    val olaQuery = TableQuery[OlaTable]
    for{
      res <- database.run(olaQuery.result)
    }yield(res)
  }

  def addOla(data:List[Ola]): Future[Seq[Ola]] ={
    val olaQuery = TableQuery[OlaTable]

    for{
      res <- database.run(olaQuery returning olaQuery.map(x => x) ++= data)
    }yield(res)
  }

  def updateOla(id:Long,data:Ola) ={
    val olaQuery = TableQuery[OlaTable]
    for{
      res <- database.run(olaQuery.filter(_.id === id).update(data))
    }yield(res)
  }

  def deleteOla(id:Long) ={
    val olaQuery = TableQuery[OlaTable]
    for{
      res <- database.run(olaQuery.filter(_.id === id).map(x => (x.isRemoved)).update((true)))
    }yield(res)
  }
}