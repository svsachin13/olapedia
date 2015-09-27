package com.rest

import akka.actor.ActorSystem
import akka.http.scaladsl.model.HttpEntity
import akka.http.scaladsl.model.HttpResponse
import akka.http.scaladsl.model.MediaTypes
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives
import akka.stream.ActorMaterializer
import akka.util.Timeout
import com.domain.Booking
import com.utils.Utilities
import org.json4s.{Extraction, DefaultFormats}
import org.json4s.jackson.JsonMethods._
import scala.concurrent.ExecutionContext.Implicits.global
import com.dao.BookingDAO
import scala.concurrent.duration._

class BookingRest(bookingRepo: BookingDAO) extends Directives {
  implicit val askTimeout: Timeout = 10.seconds

  implicit val system = ActorSystem.create("Test")

  implicit val materializer = ActorMaterializer()

  implicit val f = DefaultFormats ++ List(Utilities.SqlDateSerializer)

  val routes = path("booking") {
    post {
      entity(as[String]) { data =>
        complete {
          val parsedData = parse(data).extract[List[Booking]]
          bookingRepo.addBooking(parsedData).map { result =>
            HttpResponse(status = StatusCodes.OK, entity = HttpEntity(MediaTypes.`application/json`, compact(Extraction.decompose(result))))
          }
        }
      }
    }} ~ path("booking") {
      get {
        complete {
          bookingRepo.getBooking().map { result =>
            HttpResponse(status = StatusCodes.OK, entity = HttpEntity(MediaTypes.`application/json`, compact(Extraction.decompose(result))))
          }
        }

      }
      } ~ path("booking" / "bookingId" / LongNumber) { id =>
    put {
      entity(as[String]) { data =>
        complete {
          val parsedData = parse(data).extract[Booking]
          bookingRepo.updateBooking(id, parsedData).map { result =>
            HttpResponse(status = StatusCodes.OK, entity = HttpEntity(MediaTypes.`application/json`, compact(Extraction.decompose(result))))
          }
        }
      }
    }} ~  path("booking" / "bookingId" / LongNumber) { id =>
    delete {
      complete {
        bookingRepo.deleteBooking(id).map { result =>
          HttpResponse(status = StatusCodes.OK, entity = HttpEntity(MediaTypes.`application/json`, compact(Extraction.decompose(result))))
        }
      }
    }
  }}