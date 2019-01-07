package concurrency.tutorial

import java.util.concurrent.Callable
import java.util.concurrent.Executors
import java.util.concurrent.locks.ReentrantLock

object example_2_04 extends App {

  val executor = Executors.newFixedThreadPool(2)
  val lock = new ReentrantLock()

  object lockOneSecond extends Callable[Unit] {
    def call() = {
      lock.lock()
      try {
        Util.sleep(1)
      } finally {
        lock.unlock()
      }
    }
  }

  object printLockStatus extends Callable[Unit] {
    def call() = {
      println("Locked: " + lock.isLocked())
      println("Held by me: " + lock.isHeldByCurrentThread())
      val locked = lock.tryLock()
      println("Lock acquired: " + locked)
    }
  }

  executor.submit(lockOneSecond)
  executor.submit(printLockStatus)

  Util.stop(executor)

}