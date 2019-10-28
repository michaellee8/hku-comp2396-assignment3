package com.example.hku.comp2396.assignment3;

import java.util.HashMap;

public class CoinPack implements Cloneable {

  private HashMap<Integer, Integer> coins;

  // clone() provides a copy of the CoinPack that can be safely modified
  public Object clone() {
    CoinPack pack = (CoinPack) super.clone();
    pack.coins = (HashMap<Integer, Integer>) coins.clone();
    return pack;
  }

  public Integer getSum() {
    return coins.entrySet().stream().mapToInt(
        integerIntegerEntry -> integerIntegerEntry.getValue() * integerIntegerEntry.getKey()).sum();
  }

  public Integer

}
