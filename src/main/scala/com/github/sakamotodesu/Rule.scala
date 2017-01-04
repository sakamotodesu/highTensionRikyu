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
    ChakaiResult(List.range(1, num + 1).map(Player(_, List(1, 2, 3, 4, 5), 0, List())), List())
  }

  def chakai(seasons: List[String], playerList: List[Player], restOfChagashi: List[Int], heartOfRikyu: List[Int]): ChakaiResult = {
    if (seasons.isEmpty) {
      ChakaiResult(playerList, heartOfRikyu)
    } else {
      val kenjou = new randomKenjo
      val hodokoshi = new randomHodokoshi
      println(seasons.head)
      val afterKenjoPlayer = playerList.map(p => p.select(kenjou))
      val afterHeart = judgeChaki(afterKenjoPlayer).selectChaki :: heartOfRikyu
      println(afterKenjoPlayer)
      println(afterHeart)
      chakai(seasons.tail, afterKenjoPlayer, restOfChagashi, afterHeart)
    }
  }

  def judgeChaki(playerList: List[Player]): Player = {
    if (playerList.isEmpty) {
      Player(0, List(), 0, List())
    } else if (playerList.size == 1) {
      playerList.head
    } else {
      if (playerList.map(_.selectChaki).count(_ == playerList.head.selectChaki) == 1) {
        playerList.head
      } else {
        judgeChaki(playerList.filter(_.selectChaki != playerList.head.selectChaki))
      }
    }
  }

}
