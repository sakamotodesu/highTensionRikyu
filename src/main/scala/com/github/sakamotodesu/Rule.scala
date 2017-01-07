package com.github.sakamotodesu

/**
  * Created by sakamotominoru on 2017/01/01.
  */
object Rule {


  def main(args: Array[String]) {
    val num = 4
    val allChaki = List.fill(6)(List(1, 2, 3, 4, 5)).flatten
    val allChagashi: List[Int] = List.fill(6)(List(3, 4, 5, 6, 7)).flatten

    val playerList = prepare(num)

    val seasons = List("Spring", "Summer", "Fall", "Winter")
    chakai(seasons, playerList, allChagashi, List())
  }

  def prepare(num: Int): List[Player] = {
    val r = scala.util.Random
    selectStart(List.range(1, num + 1).map(Player(_, List(1, 2, 3, 4, 5), 0, List())), r.nextInt(num))
  }

  def selectStart(playerList: List[Player], num: Int): List[Player] = {
    if (num == 0) {
      playerList
    } else {
      selectStart((playerList.head :: playerList.tail.reverse).reverse, num - 1)
    }
  }

  def chakai(seasons: List[String], playerList: List[Player], restOfChagashi: List[Int], heartOfRikyu: List[Int]): ChakaiResult = {
    if (seasons.isEmpty) {
      ChakaiResult(playerList, heartOfRikyu)
    } else {
      val kenjou = new randomKenjo
      val hodokoshi = new randomHodokoshi
      println(seasons.head)
      val afterKenjouPlayerList = playerList.map(p => p.select(kenjou))
      val kenjouPlayer = judgeChaki(afterKenjouPlayerList)
      val afterHeart = kenjouPlayer.selectChaki :: heartOfRikyu
      val i = afterKenjouPlayerList.indexOf(kenjouPlayer)
      val forHodokoshi = selectStart(afterKenjouPlayerList, i).reverse
      val (afterChakai, rest) = chagashiHodokoshi(forHodokoshi, restOfChagashi, hodokoshi, 4)
      println(afterKenjouPlayerList)
      println(afterChakai)
      println(afterHeart)
      chakai(seasons.tail, afterChakai, rest, afterHeart)
    }
  }

  def chagashiHodokoshi(playerList: List[Player], restOfChagashi: List[Int], h: hodokoshi, count: Int): (List[Player], List[Int]) = {
    println(restOfChagashi)
    if (count == 0) {
      (playerList, restOfChagashi)
    } else {
      val c = h.select(restOfChagashi)
      val i = restOfChagashi.indexOf(c)
      val p = Player(playerList.head.id, playerList.head.chakiList, playerList.head.selectChaki, c :: playerList.head.chagashi)
      chagashiHodokoshi((p :: playerList.tail.reverse).reverse,
        restOfChagashi.take(i) ::: restOfChagashi.drop(i + 1), h, count - 1)
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
