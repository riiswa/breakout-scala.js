package com.github.breakout_scalajs

import scala.scalajs.js.timers.SetIntervalHandle

object Global {
  var refreshId: SetIntervalHandle = _
  val paddlePower = 12
  var paddleWidth = 100

  val brickRowCount = 12
  val brickColumnCount = 8
  val brickWidth = 73
  val brickHeight = 15
  val brickPadding = 10
  val brickOffsetTop = 30
  val brickOffsetLeft = 30
}
