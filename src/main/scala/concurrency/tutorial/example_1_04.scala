package concurrency.tutorial

import java.util.concurrent.Callable
import java.util.concurrent.TimeUnit
import java.util.concurrent.Executors

object example_1_04 extends App {
  object task extends Callable[Int] {
    def call(): Int = {
      try {
        TimeUnit.SECONDS.sleep(1)
        123
      } catch {
        case e: InterruptedException => throw new IllegalStateException("task interrupted", e)
      }
    }
  }

  val executor = Executors.newFixedThreadPool(1)
  val future = executor.submit(task)

  println(s"future done? ${future.isDone()}")

  val result = future.get()

  println(s"future done? ${future.isDone()}")
  println(s"result: $result")

  Util.shutdown(executor)
}