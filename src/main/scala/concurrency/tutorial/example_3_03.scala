package concurrency.tutorial

import java.util.concurrent.Executors
import java.util.concurrent.atomic.AtomicInteger
import scala.compat.java8.FunctionConverters._

object example_3_03 extends App {
  val atomicInt = new AtomicInteger(0)
  val executor = Executors.newFixedThreadPool(2)

  def runnable(i: Int): Runnable = {
    new Runnable {
      def run() = {
        atomicInt.accumulateAndGet(i, asJavaIntBinaryOperator(_ + _))
      }
    }
  }

  for (i <- 0 until 1000) executor.submit(runnable(i))

  Util.stop(executor)

  println(atomicInt.get())
}