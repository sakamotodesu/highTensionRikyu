package com.github.sakamotodesu

import org.scalatest.FunSuite

/**
  * Created by sakamotominoru on 2017/01/07.
  */
class RingListTest extends FunSuite {

  test("RingList") {
    val r = RingList(List.range(0, 6))
    assert(r.current === 0)
    assert(r.next === 1)
    assert(r.current === 1)
    assert(r.next === 2)
    assert(r.current === 2)
    assert(r.next === 3)
    assert(r.current === 3)
    assert(r.next === 4)
    assert(r.current === 4)
    assert(r.next === 5)
    assert(r.current === 5)
    assert(r.next === 0)
    assert(r.current === 0)
    assert(r.next === 1)
    assert(r.current === 1)
  }

  test("RingList reverse") {
    val r = RingList(List.range(0, 6))
    assert(r.current === 0)
    assert(r.next === 1)
    assert(r.next === 2)
    val rr = r.reverse
    assert(rr.current === 2)
    assert(rr.next === 1)
    assert(rr.next === 0)
    assert(rr.next === 5)
    assert(rr.next === 4)

    val r2 = RingList(List.range(0, 7))
    assert(r2.current === 0)
    assert(r2.next === 1)
    assert(r2.next === 2)
    val rr2 = r2.reverse
    assert(rr2.current === 2)
    assert(rr2.next === 1)
    assert(rr2.next === 0)
    assert(rr2.next === 6)
    assert(rr2.next === 5)

  }

  test("RingList seek") {
    val r = RingList(List.range(0, 6))
    assert(r.seek(3) === Some(3))
    assert(r.current === 3)
    assert(r.next === 4)
    assert(r.next === 5)
    assert(r.seek(2) === Some(2))
    assert(r.current === 2)

    val r2 = RingList(List(0, 1, 2, 3, 0, 4, 5, 6))
    assert(r2.current === 0)
    assert(r2.seek(1) === Some(1))
    assert(r2.seek(2) === Some(2))
    assert(r2.current === 2)
    assert(r2.seek(0) === Some(0))
    assert(r2.current === 0)
    assert(r2.next === 4)
    assert(r2.seek(0) === Some(0))
    assert(r2.current === 0)
    assert(r2.next === 1)

  }
}
