package concurrency.tutorial

import java.util.concurrent._

object example_1_03 extends App {
  val executor = Executors.newSingleThreadExecutor()

  object task extends Runnable {
    def run {
      val threadName = Thread.currentThread().getName()
      println("Hello " + threadName)
    }
  }

  executor.submit(task)

  Util.shutdown(executor)
}