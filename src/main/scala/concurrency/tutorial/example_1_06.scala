package concurrency.tutorial

import java.util.concurrent.Callable
import java.util.concurrent.Executors

import scala.collection.JavaConverters.asScalaBufferConverter
import scala.collection.JavaConverters.seqAsJavaListConverter

object example_1_06 extends App {
  def callable(result: String): Callable[String] = {
    new Callable[String] {
      def call() = result
    }
  }

  val tasks = List(callable("task1"),
      callable("task2"),
      callable("task3")).asJava

  val executor = Executors.newFixedThreadPool(1)

  val futures = executor.invokeAll(tasks).asScala
  futures.map(future => future.get()).foreach(println(_))

  Util.shutdown(executor)
}