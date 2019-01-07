package concurrency.tutorial

import java.util.concurrent.Callable
import java.util.concurrent.Executors

object example_2_02 extends App {
  
  var count = 0
  
  def incrementSync() = synchronized {
    count = count + 1  
  }
  
  object task extends Callable[Unit] {
    def call() = {
      incrementSync()
    }
  }

  val executor = Executors.newFixedThreadPool(2)
  
  for (i <- 1 to 10000) executor.submit(task) 

  Util.stop(executor)

  println(s"count: $count")

}