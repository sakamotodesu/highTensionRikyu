package com.github.sakamotodesu

/**
  * Created by sakamotominoru on 2017/01/01.
  */
object Rule {

  val maxChakiGrade = 5
  val playerNum = 4

  def main(args: Array[String]) {

    val allChagashi: List[Int] = scala.util.Random.shuffle(List.fill(6)(List(3, 4, 5, 6, 7)).flatten)
    val playerList = prepare(playerNum)
    val seasons = List("Spring", "Summer", "Fall", "Winter")
    val heart = HeartOfRikyu(List(), List(), List(), List())
    val result = chakai(seasons, playerList, allChagashi, heart)

  }

  def prepare(num: Int): RingList[Player] = {
    val r = scala.util.Random
    val rl = RingList(List.range(1, num + 1).map(Player(_, List.range(1, maxChakiGrade), new RandomHodokoshi, new RandomKenjo)))
    for (i <- 0 to r.nextInt(num)) {
      rl.next
    }
    rl
  }

  def chakai(seasons: List[String], playerList: RingList[Player], restOfChagashi: List[Int], heart: HeartOfRikyu): ChakaiResult = {
    if (seasons.isEmpty) {
      ChakaiResult(playerList, heart)
    } else {
      println("===" + seasons.head + "===")
      val hheart = HeartOfRikyu(heart.heart, List(), heart.hazukashimeChaki, heart.collectChagashi)
      println(playerList)
      println("start! " + hheart)
      val (afterKenjouPlayerList, hh) = selectChaki(playerList, hheart)
      println("select chaki:" + hh)
      val afterChakiHeart = hh.judgeChaki(maxChakiGrade)
      println("judge! " + afterChakiHeart)

      val startHodokoshiPlayer = afterChakiHeart.heart.head._1
      afterKenjouPlayerList.seek(x => x.id == startHodokoshiPlayer)

      val (restChagashi, afterHodokoshi) = chagashiHodokoshi(afterKenjouPlayerList.reverse, restOfChagashi, afterChakiHeart, 4)

      chakai(seasons.tail, afterKenjouPlayerList, restChagashi, afterHodokoshi)
    }
  }

  def selectChaki(playerList: RingList[Player], heart: HeartOfRikyu): (RingList[Player], HeartOfRikyu) = {
    if (heart.chakiElection.size == playerNum) {
      (playerList, heart)
    } else {
      val player = playerList.current
      val chaki = player.k.select(heart, player)
      val after = heart.addCandidateChaki(player.id, chaki, omote = true)
      val pp = playerList.replaceCurrent(Player(player.id, player.chakiList.filterNot(_ == chaki), player.h, player.k))
      pp.next
      selectChaki(pp, after)
    }
  }

  def chagashiHodokoshi(playerList: RingList[Player], chagashiList: List[Int], heart: HeartOfRikyu, count: Int): (List[Int], HeartOfRikyu) = {
    if (count == 0) {
      (chagashiList, heart)
    } else {
      val player = playerList.current
      val chagashi = player.h.select(heart, player, chagashiList)
      playerList.next
      val rest = chagashiList.take(chagashiList.indexOf(chagashi)) ::: chagashiList.drop(chagashiList.indexOf(chagashi) + 1)
      chagashiHodokoshi(playerList, rest, heart.addChagashi(player.id, chagashi), count - 1)

    }
  }

  }
