package com.github.sakamotodesu

/**
  * Created by sakamotominoru on 2017/01/03.
  */
trait Kenjou {

  def select(chakiList: List[Int]): Int

}

class easyKenjo extends Kenjou {
  override def select(chakiList: List[Int]): Int = chakiList.head
}

class randomKenjo extends Kenjou {
  override def select(chakiList: List[Int]): Int = scala.util.Random.shuffle(chakiList).head
}