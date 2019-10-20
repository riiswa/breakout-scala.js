package com.github.breakout_scalajs.item

import com.github.breakout_scalajs.controller.Drawer

case class Ball(x: Int, y: Int, dx: Int, dy: Int) {
  val radius = 10
  def next(dx: Int, dy: Int) = Ball(x + dx, y + dy, dx, dy)
  def draw(): Unit =
    Drawer.drawOnCtx({
      Drawer.ctx.arc(x, y, radius, 0, Math.PI * 2)
      Drawer.ctx.fillStyle = "#000000"
      Drawer.ctx.fill
    })
}
