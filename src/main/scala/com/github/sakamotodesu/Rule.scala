package com.github.sakamotodesu

/**
  * Created by sakamotominoru on 2017/01/01.
  */
object Rule {


  def main(args: Array[String]) {
    val num = 4
    val allChaki = List.fill(6)(List(1, 2, 3, 4, 5)).flatten
    val allChagashi: List[Int] = List.fill(6)(List(3, 4, 5, 6, 7)).flatten

    val initChakai = prepare(num)

    val seasons = List("Spring", "Summer", "Fall", "Winter")
    chakai(seasons, initChakai.playerList, allChagashi, List())
  }

  def prepare(num: Int): ChakaiResult = {
    ChakaiResult(List.range(0, num).map(Player(_, List(1, 2, 3, 4, 5), List())), List())
  }

  def chakai(seasons: List[String], playerList: List[Player], restOfChagashi: List[Int], heartOfRikyu: List[Int]): ChakaiResult = {
    if (seasons.isEmpty) {
      ChakaiResult(playerList, heartOfRikyu)
    } else {
      val kenjou = new randomKenjo
      val hodokoshi = new randomHodokoshi
      println(seasons.head)
      val chakiList = playerList.map(p => kenjou.select(p.chaki))
      val afterKenjo = playerList.map(p => Player(p.id, p.chaki.filterNot(_ == kenjou.select(p.chaki)), p.chagashi))
      val afterHeart = judgeChaki(chakiList) :: heartOfRikyu
      println(chakiList)
      println(afterKenjo)
      println(afterHeart)
      chakai(seasons.tail, afterKenjo, restOfChagashi, afterHeart)
    }
  }

  def judgeChaki(chakiList: List[Int]): Int = {
    if (chakiList.isEmpty) {
      0
    } else if (chakiList.size == 1) {
      chakiList.head
    } else {
      if (chakiList.count(_ == chakiList.sorted.head) == 1) {
        chakiList.head
      } else {
        judgeChaki(chakiList.filter(_ != chakiList.sorted.head))
      }
    }
  }

}
