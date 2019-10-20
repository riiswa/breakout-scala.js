package com.github.breakout_scalajs.controller

import com.github.breakout_scalajs.Global
import org.scalajs.dom
import org.scalajs.dom.document

object ActionListener {
  def setKeyHandler(): Unit = {
    document.addEventListener(
      "keydown", { event: dom.Event =>
        val e = event.asInstanceOf[dom.KeyboardEvent]
        val paddle = Drawer.paddle
        e.keyCode match {
          case 39 if paddle.x < Drawer.canvas.width - paddle.paddleWidth =>
            Drawer.paddle = paddle.next(paddle.x + Global.paddlePower)
          case 37 if paddle.x > 0 =>
            Drawer.paddle = paddle.next(paddle.x - Global.paddlePower)
          case _ =>
        }
      },
      false
    )
  }
  def setMouseHandler(): Unit = {
    document.addEventListener(
      "mousemove", { event: dom.Event =>
        val e = event.asInstanceOf[dom.MouseEvent]
        val paddle = Drawer.paddle
        val relativeX = e.clientX - Drawer.canvas.offsetLeft
        if (relativeX > 0 && relativeX < Drawer.canvas.width)
          Drawer.paddle = paddle.next(relativeX.toInt - paddle.paddleWidth / 2)
      },
      false
    )
  }
}
