package concurrency.tutorial

import java.util.concurrent.Callable
import java.util.concurrent.Executors
import java.util.concurrent.locks.ReentrantReadWriteLock

object example_2_05 extends App {

  val executor = Executors.newFixedThreadPool(2)
  val lock = new ReentrantReadWriteLock()
  var map = Map[String, String]()
  
  object writeTask extends Callable[Unit] {
    def call() = {
      lock.writeLock().lock()
      try {
        Util.sleep(1)
        map += ("foo" -> "bar")
      } finally {
        lock.writeLock().unlock()
      }
    }
  }

  object readTask extends Callable[Unit] {
    def call() = {
      lock.readLock().lock()
      try {
        println(map.get("foo"))
        Util.sleep(1)
      }
      finally {
        lock.readLock().unlock()
      }
    }
  }

  executor.submit(writeTask)
  executor.submit(readTask)
  executor.submit(readTask)

  Util.stop(executor)

}