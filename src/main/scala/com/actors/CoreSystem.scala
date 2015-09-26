package com.actors

import akka.actor.ActorSystem

/**
 * Created by sachin on 8/25/2015.
 * MyProject
 */

trait CoreSystem {
  implicit lazy val system = ActorSystem("ActorSystem")

  sys.addShutdownHook(system.shutdown)
}