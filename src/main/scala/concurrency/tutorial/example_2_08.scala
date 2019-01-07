package concurrency.tutorial

import java.util.concurrent.Callable
import java.util.concurrent.Executors
import java.util.concurrent.locks.StampedLock

object example_2_08 extends App {

  val executor = Executors.newFixedThreadPool(2)
  val lock = new StampedLock()
  var count = 0
  
  object task extends Callable[Unit] {
    def call() = {
      var stamp = lock.readLock()
      try {
        if (count == 0) {
          stamp = lock.tryConvertToWriteLock(stamp)
          if (stamp == 0L) {
            println("Could not convert to write lock")
            stamp = lock.writeLock()
          }
          count = 23
        }
        
        println(count);
      } finally {
        lock.unlock(stamp);
      }
    }
  }

  executor.submit(task)

  Util.stop(executor)

}