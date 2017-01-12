package com.github.sakamotodesu

import org.scalatest.FunSuite

/**
  * Created by sakamotominoru on 2017/01/03.
  */
class RuleTest extends FunSuite {

  test("judgeChaki") {
    assert(judge(List(1, 2, 3, 4, 5), 1).heart.head._2 === 1)
    assert(judge(List(1, 1, 3, 4, 5), 1).heart.head._2 === 3)
    assert(judge(List(1, 1, 3, 3, 5), 1).heart.head._2 === 5)
    assert(judge(List(4, 5, 3, 2, 2), 1).heart.head._2 === 3)
    assert(judge(List(5, 5, 2, 2, 2), 1).heart.isEmpty)
  }

  def judge(chakiList: List[Int], expected: Int): HeartOfRikyu = {
    val num = 5
    addChaki(chakiList, HeartOfRikyu(List(), List(), List(), List()), num).judgeChaki(num)
  }

  def addChaki(chakiList: List[Int], heart: HeartOfRikyu, id: Int): HeartOfRikyu = {
    if (chakiList.size == 1) {
      heart.addCandidateChaki(0, chakiList.head, omote = true)
    } else {
      addChaki(chakiList.tail,
        heart.addCandidateChaki(0, chakiList.head, omote = true),
        id + 1)
    }

  }
}
