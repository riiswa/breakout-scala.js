package com.github.breakout_scalajs.controller

import com.github.breakout_scalajs.item.{Ball, Brick, Score}
import com.github.breakout_scalajs.Global

object CollisionManager {
  def isCollideBrick(c: Int, r: Int): Boolean = {
    val b: Brick = Drawer.getBricks(c)(r)
    if (Drawer.ball.x > b.x - 3 && Drawer.ball.x < b.x + Global.brickWidth + 3 && Drawer.ball.y > b.y &&
        Drawer.ball.y < b.y + Global.brickHeight && Drawer.bricks(c)(r)) true
    else false
  }

  def updateBall(ball: Ball): Option[Ball] = {
    val ballBetweenPaddle = ball.x > Drawer.paddle.x - 5 && ball.x < Drawer.paddle.x + Drawer.paddle.paddleWidth + 5
    val gameOverCondition = ball.y + ball.dy > Drawer.canvas.height - ball.radius

    val bricksCollision: IndexedSeq[Boolean] = (for (c <- Drawer.bricks.indices)
      yield
        for (r <- Drawer.bricks.head.indices)
          yield
            if (isCollideBrick(c, r)) {
              Drawer.bricks(c)(r) = false
              Score.score += 1 * Score.coeff
              Score.coeff += 1
              true
            } else false).flatten
    if (gameOverCondition && !ballBetweenPaddle) None
    else {
      def dx =
        if (ball.x + ball.dx > Drawer.canvas.width - ball.radius || ball.x + ball.dx < ball.radius)
          -ball.dx
        else ball.dx
      def dy =
        if (ball.y + ball.dy < ball.radius || gameOverCondition && ballBetweenPaddle || bricksCollision
              .contains(true)) -ball.dy
        else ball.dy
      Some(
        ball.next(
          if (gameOverCondition && ballBetweenPaddle) {
            Score.coeff = 1; Global.paddleWidth -= 4;
            ball.dx + -1 * ((Drawer.paddle.x + Drawer.paddle.paddleWidth / 2) - ball.x) / 6
          } else dx,
          dy
        ))
    }
  }
}
