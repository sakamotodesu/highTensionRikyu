package com.github.sakamotodesu

import org.scalatest.FunSuite

/**
  * Created by sakamotominoru on 2017/01/13.
  */
class HeartOfRikyuTest extends FunSuite {

  test("tension") {
    assert(HeartOfRikyu(List((1, 1)), List(), List(), List()).tension() == 5)
    assert(HeartOfRikyu(List((1, 2)), List(), List(), List()).tension() == 4)
    assert(HeartOfRikyu(List((1, 3)), List(), List(), List()).tension() == 3)
    assert(HeartOfRikyu(List((1, 4)), List(), List(), List()).tension() == 2)
    assert(HeartOfRikyu(List((1, 5)), List(), List(), List()).tension() == 1)

    assert(HeartOfRikyu(List((1, 1), (2, 1)), List(), List(), List()).tension() == 10)
    assert(HeartOfRikyu(List((1, 1), (2, 2)), List(), List(), List()).tension() == 9)
    assert(HeartOfRikyu(List((1, 1), (2, 3)), List(), List(), List()).tension() == 8)
    assert(HeartOfRikyu(List((1, 1), (2, 3), (3, 5)), List(), List(), List()).tension() == 9)
  }

  test("total chagashi") {
    assert(HeartOfRikyu(List(), List(), List(), List((1, 1))).totalChagashi() == List((1, 1)))
    assert(HeartOfRikyu(List(), List(), List(), List((1, 1), (1, 1))).totalChagashi() == List((1, 2)))
    assert(HeartOfRikyu(List(), List(), List(), List((1, 1), (2, 1))).totalChagashi() == List((2, 1), (1, 1)))
    assert(HeartOfRikyu(List(), List(), List(), List((1, 1), (2, 2), (1, 3), (2, 4))).totalChagashi() == List((2, 6), (1, 4)))
  }
}
