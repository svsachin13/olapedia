package com.actors

import akka.actor.Actor.Receive
import akka.actor.{Actor, ActorSystem}
import com.domain.Booking
import scala.concurrent.duration._

/**
 * Created by sachin on 8/25/2015.
 * MyProject
 */

trait CoreSystem {
  implicit lazy val system = ActorSystem("ActorSystem")

  sys.addShutdownHook(system.shutdown)
}

class MyActor extends Actor{
  val system=ActorSystem.create("system1")
  import system.dispatcher
  override def preStart()={
    system.scheduler.schedule(5 minutes,
      5 minutes,
      self,
      "hello")
  }
  override def receive: Receive =
  {
    case cmd:Booking=>"received booking"
    case "hello" => //check database and do the bookings using ola api before 20 minutes of the start time
  }
}