package com.utils

import akka.actor.{ActorContext, Actor, Props}
import com.actors.CoreSystem
import com.dao.HotelDAO
import com.rest.HotelRest
import spray.routing._

/**
 * Created by sachin on 8/25/2015.
 * MyProject
 */
trait RestServices extends CoreSystem{

  val availableRoutes = new HotelRest(new HotelDAO).hotelRest

  val routeService = system.actorOf(Props(classOf[MyHttpService], availableRoutes),"RouteService")
}

class MyHttpService(route: Route) extends Actor with HttpService {

  implicit def actorRefFactory: ActorContext = context

  def receive: Receive = {
    runRoute(route)
  }
}