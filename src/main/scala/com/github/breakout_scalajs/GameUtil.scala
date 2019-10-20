package com.github.breakout_scalajs

import org.scalajs.dom
import org.scalajs.dom.document

import scala.scalajs.js

object GameUtil {
  def reload(message: String): Unit = {
    js.timers.clearInterval(Global.refreshId)
    dom.window.alert(message)
    document.location.reload(true)
  }
  def gameOver(): Unit = reload("GAME OVER !")

  def win(): Unit = reload("YOU WIN, CONGRATULATIONS!")
}
