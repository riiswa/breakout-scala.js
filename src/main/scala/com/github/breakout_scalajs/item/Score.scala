package com.github.breakout_scalajs.item

import com.github.breakout_scalajs.controller.Drawer

object Score {
  var coeff = 1
  var score = 0

  def drawScore(): Unit = {
    Drawer.ctx.font = "16px Arial"
    Drawer.ctx.fillStyle = "#0095DD"
    Drawer.ctx.fillText("Score: " + score, 8, 20)
  }
}
