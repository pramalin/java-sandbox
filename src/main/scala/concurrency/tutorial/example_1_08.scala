package concurrency.tutorial

import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

object example_1_08 extends App {

  val executor = Executors.newScheduledThreadPool(1)

  object task extends Runnable {
    def run {
      println("Scheduling: " + System.nanoTime())
    }
  }

  val future = executor.schedule(task, 3, TimeUnit.SECONDS)
  TimeUnit.MILLISECONDS.sleep(1337)

  val remainingDelay = future.getDelay(TimeUnit.MILLISECONDS)
  println(f"Remaining Delay: $remainingDelay ms")

  Util.shutdown(executor)
}