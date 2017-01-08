package com.github.sakamotodesu

/**
  * Created by sakamotominoru on 2017/01/03.
  */
trait Hodokoshi {

  def select(heart: HeartOfRikyu, me: Player, chagashiList: List[Int]): Int

  override def toString: String = "hodokoshi"
}

class EasyHodokoshi() extends Hodokoshi {
  override def select(heart: HeartOfRikyu, me: Player, chagashiList: List[Int]): Int = chagashiList.head
}

class RandomHodokoshi() extends Hodokoshi {
  override def select(heart: HeartOfRikyu, me: Player, chagashiList: List[Int]): Int = scala.util.Random.shuffle(chagashiList).head
}