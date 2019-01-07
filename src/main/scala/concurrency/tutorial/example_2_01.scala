package concurrency.tutorial

import java.util.concurrent.Callable
import java.util.concurrent.Executors

object example_2_01 extends App {
  
  var count = 0
  
  def increment() {
    count = count + 1  
  }
  
  object task extends Callable[Unit] {
    def call() = {
      increment()
    }
  }

  val executor = Executors.newFixedThreadPool(2)
  
  for (i <- 1 to 10000) executor.submit(task) 

  Util.stop(executor)

  println(s"count: $count")

}