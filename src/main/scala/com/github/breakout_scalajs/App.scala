package com.github.breakout_scalajs

import com.github.breakout_scalajs.controller.{ActionListener, Drawer}

import scala.scalajs.js

object App {
  def main(args: Array[String]): Unit = {
    ActionListener.setKeyHandler()
    ActionListener.setMouseHandler()
    Global.refreshId = js.timers.setInterval(10)(Drawer.draw())
  }
}
