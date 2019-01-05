package concurrency.tutorial

import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

object example_1_10 extends App {
  val executor = Executors.newScheduledThreadPool(1)

  object task extends Runnable {
    def run {
      try {
        TimeUnit.SECONDS.sleep(2)
        println("Scheduling: " + System.nanoTime())
      } catch {
        case e: InterruptedException => println("task interrupted")
      }
    }
  }

  executor.scheduleWithFixedDelay(task, 0, 1, TimeUnit.SECONDS)

  TimeUnit.SECONDS.sleep(10) // wait for demo
  Util.shutdown(executor)
}