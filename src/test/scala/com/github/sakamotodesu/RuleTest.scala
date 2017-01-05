package com.github.sakamotodesu

import org.scalatest.FunSuite

/**
  * Created by sakamotominoru on 2017/01/03.
  */
class RuleTest extends FunSuite {

  test("judgeChaki") {

    assert(Rule.judgeChaki(players(List(1, 2, 3, 4, 5))).selectChaki === 1)
    assert(Rule.judgeChaki(players(List(1, 1, 3, 4, 5))).selectChaki === 3)
    assert(Rule.judgeChaki(players(List(1, 1, 3, 3, 5))).selectChaki === 5)
    assert(Rule.judgeChaki(players(List(1, 1, 1, 1, 1))).selectChaki === 0)
  }

  def players(nums: List[Int]): List[Player] = {
    val a = for (i <- 0 to 4) yield
      Player(i + 1, List(), nums(i), List())
    a.toList
  }
}
