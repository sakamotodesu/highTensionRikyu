package com.github.sakamotodesu

/**
  * Created by sakamotominoru on 2017/01/08.
  */
case class HeartOfRikyu(heart: List[(Player, Int)],
                        chakiElection: List[(Player, Int, Boolean)],
                        hazukashimeChaki: List[(Player, Int)],
                        collectChagashi: List[(Player, Int)]) {

  def addHeart(player: Player, chaki: Int): HeartOfRikyu = {
    HeartOfRikyu((player, chaki) :: heart, chakiElection, hazukashimeChaki, collectChagashi)
  }

  def addCandidateChaki(player: Player, chaki: Int, omote: Boolean): HeartOfRikyu = {
    HeartOfRikyu(heart, (player, chaki, omote) :: chakiElection, hazukashimeChaki, collectChagashi)
  }

  def addHazukashime(player: Player, chaki: Int): HeartOfRikyu = {
    HeartOfRikyu(heart, chakiElection, (player, chaki) :: hazukashimeChaki, collectChagashi)
  }

  def addChagashi(player: Player, chagashi: Int): HeartOfRikyu = {
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
}


