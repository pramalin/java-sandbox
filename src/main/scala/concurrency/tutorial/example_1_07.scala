package concurrency.tutorial

import java.util.concurrent.Callable
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

import scala.collection.JavaConverters.seqAsJavaListConverter

object example_1_07 extends App {
  
  def callable(result: String, sleepSeconds: Int): Callable[String] = {
    new Callable[String] {
      def call() = {
        TimeUnit.SECONDS.sleep(sleepSeconds)
        result
      }
    }
  }

  val tasks = List(callable("task1", 2),
      callable("task2", 1),
      callable("task3", 3)).asJava

  val executor = Executors.newWorkStealingPool()

  val result = executor.invokeAny(tasks)
  println(result)

  Util.shutdown(executor)
}