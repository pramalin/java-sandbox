package concurrency.tutorial

import java.util.concurrent.Executors
import java.util.concurrent.Semaphore
import java.util.concurrent.TimeUnit

object example_2_10_semaphore extends App {
  val executor = Executors.newFixedThreadPool(10)

  val semaphore = new Semaphore(5)

  object longRunningTask extends Runnable {
    def run() = {
      var permit = false
      try {
        permit = semaphore.tryAcquire(1, TimeUnit.SECONDS)
        if (permit) {
          println("Semaphore acquired")
          Util.sleep(5)
        } else {
          println("Could not acquire semaphore")
        }
      } catch {
        case e: InterruptedException => throw new IllegalStateException(e)
      } finally {
        if (permit) {
          semaphore.release()
        }
      }
    }
  }

  for (i <- 1 to 10) executor.submit(longRunningTask)

  Util.stop(executor)
}