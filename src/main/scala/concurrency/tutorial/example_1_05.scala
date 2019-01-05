package concurrency.tutorial

import java.util.concurrent.Callable
import java.util.concurrent.TimeUnit
import java.util.concurrent.Executors

object example_1_05 extends App {
  object task extends Callable[Int] {
    def call(): Int = {
      try {
        TimeUnit.SECONDS.sleep(2)
        123
      } catch {
        case e: InterruptedException => throw new IllegalStateException("task interrupted", e)
      }
    }
  }

  val executor = Executors.newFixedThreadPool(1)
  val future = executor.submit(task)

  val result = future.get(1, TimeUnit.SECONDS)

  Util.shutdown(executor)
}