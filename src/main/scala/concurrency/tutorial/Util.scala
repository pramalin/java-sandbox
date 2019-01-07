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

  // for part 2.
  def stop(executor: ExecutorService) {
    try {
      executor.shutdown()
      executor.awaitTermination(60, TimeUnit.SECONDS)
    } catch {
      case e: InterruptedException => println("termination interrupted")
    } finally {
      if (!executor.isTerminated()) {
        println("killing non-finished tasks")
      }
      executor.shutdownNow()
    }
  }
    
  def sleep(seconds: Int) {
    try {
      TimeUnit.SECONDS.sleep(seconds)
    } catch {
      case e: InterruptedException => throw new IllegalStateException(e)
    }

  }
}