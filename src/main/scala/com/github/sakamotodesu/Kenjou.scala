package com.github.sakamotodesu

/**
  * Created by sakamotominoru on 2017/01/03.
  */
trait Kenjou {

  def select(heart: HeartOfRikyu, me: Player): Int

  override def toString: String = "kenjoa"

}

class EasyKenjo extends Kenjou {
  override def select(heart: HeartOfRikyu, me: Player): Int = me.chakiList.head
}

class RandomKenjo extends Kenjou {
  override def select(heart: HeartOfRikyu, me: Player): Int = scala.util.Random.shuffle(me.chakiList).head
}