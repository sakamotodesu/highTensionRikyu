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


  test("hantei") {
    assert(HeartOfRikyu(List((1, 1)), List(), List(), List((1, 6)))
      .hantei(RingList(List(Player(1, List(2), new RandomHodokoshi, new RandomKenjo)))) == 1)

    assert(HeartOfRikyu(List((1, 1), (2, 2)), List(), List(), List((1, 6), (2, 6), (1, 7), (2, 8)))
      .hantei(RingList(List(Player(1, List(2), new RandomHodokoshi, new RandomKenjo),
        Player(2, List(1), new RandomHodokoshi, new RandomKenjo)))) == 1)

    assert(HeartOfRikyu(List((1, 1), (2, 2)), List(), List(), List((1, 6), (2, 6), (1, 7), (2, 7)))
      .hantei(RingList(List(Player(1, List(2), new RandomHodokoshi, new RandomKenjo),
        Player(2, List(1), new RandomHodokoshi, new RandomKenjo)))) == 2)

    assert(HeartOfRikyu(List((1, 1), (2, 2), (3, 3)), List(),
      List((1, 2), (1, 3), (2, 1), (2, 3), (3, 1)), List((1, 6), (2, 6), (3, 6), (1, 7), (2, 7), (3, 7), (1, 8), (2, 8), (3, 8)))
      .hantei(RingList(List(Player(1, List(2), new RandomHodokoshi, new RandomKenjo),
        Player(2, List(1), new RandomHodokoshi, new RandomKenjo),
        Player(3, List(1), new RandomHodokoshi, new RandomKenjo)))) == 3)
  }


}
