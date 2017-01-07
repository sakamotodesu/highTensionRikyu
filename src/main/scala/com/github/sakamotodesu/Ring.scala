package com.github.sakamotodesu

/**
  * Created by sakamotominoru on 2017/01/07.
  */
trait Ring[+A] {

  def current: A

  def next: A

  def size: Int

  def reverse: Ring[A]

}

object RingList {

  def apply[A](a: List[A], id: Int = 0): RingList[A] = {
    new RingList(a, id)
  }

}

class RingList[A](list: List[A], id: Int = 0) extends Ring[A] {

  private val ringList = list
  private var currentId = id

  override def current: A = ringList(currentId)

  override def next: A = {
    if (currentId == ringList.size - 1) {
      currentId = 0
    } else {
      currentId = currentId + 1
    }
    ringList(currentId)
  }

  override def size: Int = ringList.size

  override def reverse: Ring[A] = RingList(ringList.reverse, ringList.size - 1 - currentId)

  override def toString: String = currentId.toString + " : " + ringList.toString()
}