package com.github.sakamotodesu

/**
  * Created by sakamotominoru on 2017/01/01.
  */
object Rule {

  def main(args: Array[String]) {
    val num = 4
    val allChaki = List.fill(6)(List(1, 2, 3, 4, 5)).flatten
    val allChagashi = List.fill(6)(List(3, 4, 5, 6, 7)).flatten

    println(prepare(num))

    println(allChagashi)
    
  }

  def prepare(num: Int): List[Player] = {
    List.range(0, num).map(Player(_, List(1, 2, 3, 4, 5), List()))
  }



}
