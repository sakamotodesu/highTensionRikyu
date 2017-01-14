package com.github.sakamotodesu

/**
  * Created by sakamotominoru on 2017/01/08.
  */
case class HeartOfRikyu(heart: List[(ID, Chaki)],
                        chakiElection: List[(ID, Chaki, Boolean)],
                        hazukashimeChaki: List[(ID, Chaki)],
                        collectChagashi: List[(ID, Chagashi)]) {
  // Player, Chagashi

  def addHeart(player: ID, chaki: Chaki): HeartOfRikyu = {
    HeartOfRikyu((player, chaki) :: heart, chakiElection, hazukashimeChaki, collectChagashi)
  }

  def addCandidateChaki(player: ID, chaki: Chaki, omote: Boolean): HeartOfRikyu = {
    HeartOfRikyu(heart, (player, chaki, omote) :: chakiElection, hazukashimeChaki, collectChagashi)
  }

  def addHazukashime(player: ID, chaki: Chaki): HeartOfRikyu = {
    HeartOfRikyu(heart, chakiElection, (player, chaki) :: hazukashimeChaki, collectChagashi)
  }

  def addChagashi(player: ID, chagashi: Chagashi): HeartOfRikyu = {
    HeartOfRikyu(heart, chakiElection, hazukashimeChaki, (player, chagashi) :: collectChagashi)
  }

  def judgeChaki(maxChakiGrade: Int): HeartOfRikyu = {
    for (i <- 1 to maxChakiGrade) {
      if (chakiElection.count(_._2 == i) == 1) {
        val (player, chaki, omote) = chakiElection.filter(_._2 == i).head
        return addHeart(player, chaki)
      }
    }
    this
  }

  def tension(): Int = {
    heart.foldLeft(0)((z, n) => z + (6 - n._2))
  }

  def totalChagashi(): List[(ID, Chagashi)] = {
    def rec(cha: List[(ID, Chagashi)], total: List[(ID, Chagashi)]): List[(ID, Chagashi)] = {
      if (cha.isEmpty) {
        total
      } else {
        total.find(x => x._1 == cha.head._1) match {
          case Some(x) => rec(cha.tail, (cha.head._1, cha.head._2 + x._2) :: total.filter(x != _))
          case None => rec(cha.tail, cha.head :: total)
        }
      }
    }

    rec(collectChagashi, List())
  }

  def hantei(playerList: RingList[Player]): ID = {
    val t = tension()
    println("tension : " + t)
    val total = totalChagashi()
    println("total chagashi : " + total)
    val a = total.filter(_._2 >= t)
    println("over total : " + a)
    if (a.isEmpty) {
      0
    }
    val min = a.minBy(_._2)
    println("min : " + min)
    val minChagashi = a.filter(_._2 == min._2)
    println("minChagashi : " + minChagashi)
    if (minChagashi.size == 1) {
      minChagashi.head._1
    } else {
      val p = minChagashi.flatMap(y => playerList.seek(x => x.id == y._1))
      val mm = p.minBy(_.chakiList.head)
      val goodChaki = p.filter(_.chakiList.head == mm.chakiList.head)
      if (goodChaki.size == 1) {
        goodChaki.head.id
      } else {
        val h = goodChaki.map(x => (x.id, hazukashimeChaki.count(y => x.id == y._1)))
        val hh = h.filter(_._2 == h.minBy(_._2))
        if (hh.size == 1) {
          hh.head._1
        } else {
          0
        }
      }
    }
  }


  def hazukashime(minChagashi: List[(ID, Chagashi)], hazukashimeChaki: List[(ID, Chaki)]): Unit = {

  }

}


