package concurrency.tutorial

object example_1_01 extends App {

  object task extends Runnable {
    def run {
      val threadName = Thread.currentThread().getName()
      println("Hello " + threadName)
    }
  }

  task.run()

  val thread = new Thread(task)
  thread.start()

  println("Done!")

}