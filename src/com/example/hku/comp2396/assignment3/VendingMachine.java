package com.example.hku.comp2396.assignment3;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class VendingMachine {

  public CoinPack coinChanger;
  public CoinPack coinSlot;
  public ArrayList<SoftDrinkSlot> softDrinkSlots;

  public VendingMachine() {
    coinChanger = new CoinPack();
    coinSlot = new CoinPack();
    softDrinkSlots = new ArrayList<SoftDrinkSlot>();
  }

  public void addCoinToCoinChanger(Integer c) {
    coinChanger.addCoin(c);
  }

  public void addCoinToCoinSlot(Integer c) {
    coinSlot.addCoin(c);
  }

  public void addSoftDrinkSlot(SoftDrinkSlot s) {
    softDrinkSlots.add(s);
  }


}
