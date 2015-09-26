package com.rest

import scala.concurrent.ExecutionContext.Implicits.global
import com.dao.HotelDAO
import net.liftweb.json.Serialization._
import spray.http._
import spray.routing._

class HotelRest(hotelService:HotelDAO) extends Directives{

  implicit val formats = net.liftweb.json.DefaultFormats

  val hotelRest = path("hotels") {
    get {
      respondWithMediaType(MediaTypes.`application/json`) { ctx =>
        hotelService.getHotels().map { res =>
          ctx.complete(write(res))
        }.recover{case x: Throwable=>
          ctx.complete("error")
        }
      }
    }
  }
}