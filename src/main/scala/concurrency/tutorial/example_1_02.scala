package concurrency.tutorial

import java.util.concurrent.TimeUnit

object example_1_02 extends App {

  object runnable extends Runnable {
    def run {
      try {
        val name = Thread.currentThread().getName()
        println(s"Foo $name")
        TimeUnit.SECONDS.sleep(1)
        println(s"Bar $name")
      } catch {
        case e:Exception => println(e.getStackTrace)
      }
    }
  }

  val thread = new Thread(runnable)
  thread.start()
}
