package com.github.sakamotodesu

/**
  * Created by sakamotominoru on 2017/01/01.
  */
case class Player(id: Int, chakiList: List[Int], selectChaki: Int, chagashi: List[Int]) {

  def select(f: Kenjou): Player = {
    val chaki = f.select(chakiList)
    Player(id, chakiList.filterNot(_ == chaki), chaki, chagashi)
  }
}
