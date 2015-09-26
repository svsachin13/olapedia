package com.utils

import akka.io.IO
import com.actors.CoreSystem
import com.config.Configuration
import spray.can.Http

/**
 * Created by sachin on 8/25/2015.
 * MyProject
 */
trait WebService extends RestServices with CoreSystem with Configuration{

  IO(Http) ! Http.Bind(routeService, serviceHost, servicePort)
}
