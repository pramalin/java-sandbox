package concurrency.tutorial
import java.util.concurrent._

object Util {

  /*
   * shutdown gracefully
   */
  def shutdown(executor: ExecutorService) {
    try {
      println("attempt to shutdown executor")
      executor.shutdown()
      executor.awaitTermination(5, TimeUnit.SECONDS)
    } catch {
      case e: InterruptedException => println("tasks interrupted")
    } finally {
      if (!executor.isTerminated()) {
        println("cancel non-finished tasks")
      }
      executor.shutdownNow()
      println("shutdown finished")
    }
  }
}