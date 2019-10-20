package com.github.breakout_scalajs.item

import com.github.breakout_scalajs.Global
import com.github.breakout_scalajs.controller.Drawer

case class Brick(c: Int, r: Int, color: String) {
  val x: Int = (c * (Global.brickWidth + Global.brickPadding)) + Global.brickOffsetLeft
  val y: Int = (r * (Global.brickHeight + Global.brickPadding)) + Global.brickOffsetTop
  def draw(): Unit =
    Drawer.drawOnCtx({
      Drawer.ctx.rect(x, y, Global.brickWidth, Global.brickHeight)
      Drawer.ctx.fillStyle = color
      Drawer.ctx.fill
    })
}
