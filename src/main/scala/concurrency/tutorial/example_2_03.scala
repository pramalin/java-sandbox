package concurrency.tutorial

import java.util.concurrent.Callable
import java.util.concurrent.Executors
import java.util.concurrent.locks.ReentrantLock

object example_2_03 extends App {
  
val lock = new ReentrantLock()
var count = 0

def increment() {
    lock.lock()
    try {
        count = count + 1
    } finally {
        lock.unlock()
    }
}  
  object task extends Callable[Unit] {
    def call() = {
      increment()
    }
  }

  val executor = Executors.newFixedThreadPool(2)
  
  for (i <- 1 to 10000) executor.submit(task) 

  Util.stop(executor)

  println(s"count: $count")

}