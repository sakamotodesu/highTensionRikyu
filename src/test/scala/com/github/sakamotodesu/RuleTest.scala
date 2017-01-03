package com.github.sakamotodesu

import org.scalatest.FunSuite

/**
  * Created by sakamotominoru on 2017/01/03.
  */
class RuleTest extends FunSuite {

  test("judgeChaki") {
    assert(Rule.judgeChaki(List(1, 2, 3, 4, 5)) === 1)
    assert(Rule.judgeChaki(List(1, 1, 3, 4, 5)) === 3)
    assert(Rule.judgeChaki(List(1, 1, 3, 3, 5)) === 5)
    assert(Rule.judgeChaki(List(1, 1, 1, 1, 1)) === 0)
  }

}
