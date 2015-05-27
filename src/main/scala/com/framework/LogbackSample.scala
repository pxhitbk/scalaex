package com.framework

import org.slf4j.LoggerFactory

/**
 * Created by g10-macmini on 5/7/15.
 */
class LogbackSample {
  val logger = LoggerFactory.getLogger(classOf[LogbackSample])

  def doSomeLog(): Unit = {
    logger.info("This was logged")
  }
}

object Logger {

}
