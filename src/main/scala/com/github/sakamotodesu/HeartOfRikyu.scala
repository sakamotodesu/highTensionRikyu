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
}


