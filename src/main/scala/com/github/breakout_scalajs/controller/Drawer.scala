package com.github.breakout_scalajs.controller

import com.github.breakout_scalajs.item.{Ball, Brick, Paddle, Score}
import com.github.breakout_scalajs.{GameUtil, Global}
import org.scalajs.dom
import org.scalajs.dom.{document, html}

object Drawer {
  val bricks: Array[Array[Boolean]] =
    Array.fill[Boolean](Global.brickColumnCount, Global.brickRowCount)(
      if (scala.util.Random.nextFloat() > 0.4) true
      else false)

  val colors: Array[Array[String]] =
    Array.fill[String](Global.brickColumnCount, Global.brickRowCount)(
      "#%06x".format(scala.util.Random.nextInt(1 << 24)))

  def getBricks: IndexedSeq[IndexedSeq[Brick]] =
    for (c <- bricks.indices)
      yield for (r <- bricks.head.indices) yield Brick(c, r, colors(c)(r))

  def drawBricks(): Unit =
    for (c <- bricks.indices)
      for (r <- bricks.head.indices) if (bricks(c)(r)) getBricks(c)(r).draw()

  var ball = Ball(canvas.width / 2, canvas.height - 300, 1, 2)

  var paddle = Paddle((canvas.width - 75) / 2)

  def canvas: html.Canvas =
    document.getElementById("myCanvas").asInstanceOf[html.Canvas]

  def ctx: dom.CanvasRenderingContext2D =
    canvas.getContext("2d").asInstanceOf[dom.CanvasRenderingContext2D]

  def clear(): Unit = ctx.clearRect(0, 0, canvas.width, canvas.height)

  def drawOnCtx[R](block: => R): Unit = {
    ctx.beginPath
    block
    ctx.closePath
  }

  def draw(): Unit = {
    clear()
    Score.drawScore()
    ball.draw()
    paddle.draw()
    drawBricks()

    CollisionManager.updateBall(ball) match {
      case Some(updatedBall) => Drawer.ball = updatedBall
      case _                 => GameUtil.gameOver()
    }

    if (bricks.flatten.forall(_ == false)) GameUtil.win()

  }
}
