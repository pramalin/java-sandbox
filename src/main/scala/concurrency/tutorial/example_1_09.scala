package concurrency.tutorial

import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

object example_1_09 extends App {

  val executor = Executors.newScheduledThreadPool(1)

  object task extends Runnable {
    def run {
      println("Scheduling: " + System.nanoTime())
    }
  }

  val initialDelay = 0
  val period = 1
  executor.scheduleAtFixedRate(task, initialDelay, period, TimeUnit.SECONDS);

  TimeUnit.SECONDS.sleep(3) // wait for demo
  Util.shutdown(executor)
}