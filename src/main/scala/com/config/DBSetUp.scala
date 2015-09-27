package com.config

import slick.driver.PostgresDriver
/**
 * Created by sachin on 7/8/2015.
 * MyProject
 */
object MyPostgresDriver extends PostgresDriver

object DBFiles {
  import MyPostgresDriver.api._
  val database:MyPostgresDriver.backend.DatabaseDef = Database.forURL("jdbc:postgresql://localhost:5432/postgres",user="postgres",password="def123@", driver= "org.postgresql.Driver")
}