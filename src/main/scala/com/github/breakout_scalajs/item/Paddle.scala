package com.github.breakout_scalajs.item

import com.github.breakout_scalajs.Global
import com.github.breakout_scalajs.controller.Drawer

case class Paddle(x: Int) {
  val paddleHeight = 10
  val paddleWidth: Int = Global.paddleWidth match {
    case a if a > 10 => a
    case _           => 10
  }
  def next(x: Int) = Paddle(x)
  def draw(): Unit =
    Drawer.drawOnCtx({
      Drawer.ctx.rect(x,
                      Drawer.canvas.height - paddleHeight,
                      paddleWidth,
                      paddleHeight)
      Drawer.ctx.fillStyle = "#000000"
      Drawer.ctx.fill
    })
}
