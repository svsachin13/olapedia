package com.rest

import scala.concurrent.ExecutionContext.Implicits.global
import com.dao.OlaDAO
import net.liftweb.json.Serialization._
import spray.http._
import spray.routing._

class OlaRest(ola:OlaDAO) extends Directives{

  implicit val formats = net.liftweb.json.DefaultFormats

  val olaRest = path("olas") {
    get {
      respondWithMediaType(MediaTypes.`application/json`) { ctx =>
        ola.getOla().map { res =>
          ctx.complete(write(res))
        }.recover{case x: Throwable=>
          ctx.complete("error")
        }
      }
    }
  }
}