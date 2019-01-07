package concurrency.tutorial

import java.util.concurrent.Callable
import java.util.concurrent.Executors
import java.util.concurrent.locks.StampedLock

object example_2_06 extends App {

  val executor = Executors.newFixedThreadPool(2)
  val lock = new StampedLock()
  var map = Map[String, String]()
  
  object writeTask extends Callable[Unit] {
    def call() = {
      val stamp = lock.writeLock()
      try {
        Util.sleep(1)
        map += ("foo" -> "bar")
      } finally {
        lock.unlockWrite(stamp)
      }
    }
  }

  object readTask extends Callable[Unit] {
    def call() = {
      val stamp = lock.readLock()
      try {
        println(map.get("foo"))
        Util.sleep(1)
      }
      finally {
        lock.unlockRead(stamp)
      }
    }
  }

  executor.submit(writeTask)
  executor.submit(readTask)
  executor.submit(readTask)

  Util.stop(executor)
  
}