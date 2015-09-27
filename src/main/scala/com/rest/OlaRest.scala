package com.rest

import akka.actor.ActorSystem
import akka.http.scaladsl.model.HttpEntity
import akka.http.scaladsl.model.HttpResponse
import akka.http.scaladsl.model.MediaTypes
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives
import akka.stream.ActorMaterializer
import akka.util.Timeout
import com.domain.Ola
import com.utils.Utilities
import org.json4s.{Extraction, DefaultFormats}
import org.json4s.jackson.JsonMethods._
import scala.concurrent.ExecutionContext.Implicits.global
import com.dao.OlaDAO
import scala.concurrent.duration._

import scala.concurrent.Future

class OlaRest(olaRepo: OlaDAO) extends Directives {
  implicit val askTimeout: Timeout = 10.seconds

  implicit val system = ActorSystem.create("Test")

  implicit val materializer = ActorMaterializer()

  implicit val f = DefaultFormats ++ List(Utilities.SqlDateSerializer)

  val routes = path("ola") {
    post {
      entity(as[String]) { data =>
        complete {
          val parsedData = parse(data).extract[List[Ola]]
          olaRepo.addOla(parsedData).map { result =>
            HttpResponse(status = StatusCodes.OK, entity = HttpEntity(MediaTypes.`application/json`, compact(Extraction.decompose(result))))
          }
        }
      }
    }} ~ path("ola") {
      get {
        complete {
          olaRepo.getOla().map { result =>
            HttpResponse(status = StatusCodes.OK, entity = HttpEntity(MediaTypes.`application/json`, compact(Extraction.decompose(result))))
          }
        }
      }
  } ~ pathPrefix("ola" / "olaId" / LongNumber) { id =>
    put {
      entity(as[String]) { data =>
        complete {
          val parsedData = parse(data).extract[Ola]
          olaRepo.updateOla(id, parsedData).map { result =>
            HttpResponse(status = StatusCodes.OK, entity = HttpEntity(MediaTypes.`application/json`, compact(Extraction.decompose(result))))
          }
        }
      }
    } ~ delete {
      complete {
        olaRepo.deleteOla(id).map { result =>
          HttpResponse(status = StatusCodes.OK, entity = HttpEntity(MediaTypes.`application/json`, compact(Extraction.decompose(result))))
        }
      }
    }
  }
}