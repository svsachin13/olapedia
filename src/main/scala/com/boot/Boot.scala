
/**
 * Created by sachin on 8/13/2015.
 */


package com.boot

import akka.actor.{ ActorSystem}
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.RouteConcatenation
import akka.stream.ActorMaterializer
import com.dao.{BookingDAO, OlaDAO}
import com.rest.{BookingRest, OlaRest}

import scala.concurrent.ExecutionContext.Implicits.global

trait RestEndCollection extends RouteConcatenation{
  implicit val system = ActorSystem.create("Test1")

  val allRoutes = new OlaRest(new OlaDAO()).routes ~ new BookingRest(new BookingDAO()).routes
}

object Boot extends App with RestEndCollection{
  implicit val materializer = ActorMaterializer()

  val r = Http().bindAndHandle(allRoutes, interface = "0.0.0.0", port = 8080)
  r.map { x => println("Successfully Bound to " + x.localAddress) }.recover { case _ => println("Failed to Bind ") }
  Thread.sleep(5000)
}
