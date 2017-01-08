package com.github.sakamotodesu

import org.scalatest.FunSuite

/**
  * Created by sakamotominoru on 2017/01/03.
  */
class RuleTest extends FunSuite {

  test("judgeChaki") {

    val h = HeartOfRikyu(List(), List(), List(), List())
    val hh = h.addCandidateChaki(Player(0, List(), new RandomHodokoshi, new RandomKenjo), 1, omote = true)
    val hhh = hh.judgeChaki(5)
    assert(hhh.heart.head._2 === 1)
  }

}
