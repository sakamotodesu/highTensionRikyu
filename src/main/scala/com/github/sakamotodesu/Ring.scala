package com.github.sakamotodesu


/**
  * Created by sakamotominoru on 2017/01/07.
  */
trait Ring[A] {

  def current: A

  def next: A

  def size: Int

  def reverse: Ring[A]

  def seek(a: A): Option[A]

  def seek(f: A => Boolean): Option[A]

  def map[B](f: A => B): Ring[B]

  def replaceCurrent(a: A): Ring[A]

  def toList: List[A]

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

  override def reverse: RingList[A] = RingList(ringList.reverse, ringList.size - 1 - currentId)

  override def toList: List[A] = {
    val t = ringList.take(currentId)
    val d = ringList.drop(currentId)
    t ::: d
  }

  override def seek(a: A): Option[A] = {
    seek(x => x.equals(a))
  }

  override def seek(f: A => Boolean): Option[A] = {
    val t = ringList.take(currentId)
    val d = ringList.drop(currentId)
    d.find(f).flatMap { a =>
      currentId = d.indexOf(a) + t.size
      Some(d.indexOf(a))
    } match {
      case Some(a) => Some(d(a))
      case None => t.find(f).flatMap { b =>
        currentId = t.indexOf(b)
        Some(b)
      }
    }
  }


  override def map[B](f: (A) => B): RingList[B] = {
    val t = ringList.take(currentId)
    val d = ringList.drop(currentId)
    RingList(d.map(f) ::: t.map(f))
  }

  override def replaceCurrent(a: A): RingList[A] = {
    val t = ringList.take(currentId)
    val d = ringList.drop(currentId + 1)
    RingList(t ::: a :: d, currentId)
  }

  override def toString: String = currentId.toString + " : " + ringList.toString()
}