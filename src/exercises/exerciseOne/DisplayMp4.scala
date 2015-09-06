package exerciseOne

import java.io.File
import akka.actor.ActorSystem
import org.reactivestreams.api.{Consumer, Producer}
import video.Frame

object DisplayMp4 {

  /**
   * run:
   *    ./activator 'runMain exerciseOne.DisplayMp4'
   *
   */
  def main(args: Array[String]): Unit = {
    // ActorSystem represents the "engine" we run in, including threading configuration and concurrency semantics.
    val system = ActorSystem()

    // Given - The location of the mp4 we can display (note first few seconds are still frame).
    val mp4 = new File("goose.mp4")

    // ------------
    // EXERCISE 1.2
    // ------------
    // Fill in the code necessary to construct a UI display and read the mp4 file and
    // play it in the UI display.
    val fileProducer: Producer[Frame] = video.readFFmpegFile(mp4, system)
    val display: Consumer[Frame] = video.display(system)

    fileProducer.produceTo(display)
  }
}
