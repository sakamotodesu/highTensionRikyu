package com.github.sakamotodesu

/**
  * Created by sakamotominoru on 2017/01/03.
  */
trait hodokoshi {

  def select(chagashiList: List[Int]): Int

}

class easyHodokoshi() extends hodokoshi {
  override def select(chagashiList: List[Int]): Int = chagashiList.head
}

class randomHodokoshi() extends hodokoshi {
  override def select(chagashiList: List[Int]): Int = scala.util.Random.shuffle(chagashiList).head
}