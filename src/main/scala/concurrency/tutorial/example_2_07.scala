package concurrency.tutorial

import java.util.concurrent.Callable
import java.util.concurrent.Executors
import java.util.concurrent.locks.StampedLock

object example_2_07 extends App {

  val executor = Executors.newFixedThreadPool(2)
  val lock = new StampedLock()

  object optimisticTask extends Callable[Unit] {
    def call() = {
      val stamp = lock.tryOptimisticRead()
      try {
        println(s"stamp: $stamp Optimistic Lock Valid: ${lock.validate(stamp)}")
        Util.sleep(1)
        println("Optimistic Lock Valid: " + lock.validate(stamp))
        Util.sleep(2)
        println("Optimistic Lock Valid: " + lock.validate(stamp))
      } finally {
        lock.unlock(stamp)
      }
    }
  }

  executor.submit(optimisticTask)

  object writeTask extends Callable[Unit] {
    def call() = {
      Util.sleep(1)
      val stamp = lock.writeLock()
      try {
        println("Write Lock acquired")
        Util.sleep(2)
      } finally {
        lock.unlock(stamp)
        println("Write done")
      }
    }
  }

  executor.submit(writeTask)

  Util.stop(executor)

}