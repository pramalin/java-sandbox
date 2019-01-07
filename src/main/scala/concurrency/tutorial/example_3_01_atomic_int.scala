package concurrency.tutorial

import java.util.concurrent.Executors
import java.util.concurrent.atomic.AtomicInteger

object example_3_01_atomic_int extends App {
  val atomicInt = new AtomicInteger(0)
  val executor = Executors.newFixedThreadPool(2)

  object task extends Runnable {
    def run {
      atomicInt.incrementAndGet()
    }
  }

  for (i <- 1 to 1000) executor.submit(task)

  Util.stop(executor)

  println(atomicInt.get())
}