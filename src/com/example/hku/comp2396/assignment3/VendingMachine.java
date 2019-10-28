package com.example.hku.comp2396.assignment3;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class VendingMachine {
    private HashMap<Integer, Integer> coinChanger;
    private HashMap<Integer, Integer> coinSlot;
    private ArrayList<SoftDrinkSlot> softDrinkSlots;

    public VendingMachine() {
        coinChanger = new HashMap<Integer, Integer>();
        coinSlot = new HashMap<Integer, Integer>();
        softDrinkSlots = new ArrayList<SoftDrinkSlot>();
    }

    public void addCoinToCoinChanger(Integer c) {
        if (coinChanger.containsKey(c)) {
            coinChanger.put(c, coinChanger.get(c) + 1);
        } else {
            coinChanger.put(c, 1);
        }
    }

    public void addCoinToCoinSlot(Integer c) {
        if (coinSlot.containsKey(c)) {
            coinSlot.put(c, coinSlot.get(c) + 1);
        } else {
            coinSlot.put(c, 1);
        }
    }

    public void addSoftDrinkSlot(SoftDrinkSlot s) {
        softDrinkSlots.add(s);
    }

    public bool attemptChangeCoin(Integer amount) {

    	// Get a list of coin values that can be changed in descending order
        List<Integer> coinTypes = new ArrayList<Integer>();
        coinTypes.addAll(coinChanger.keySet());
        coinTypes.sort(Comparator.reverseOrder());

        for (int i = 0;i < coinTypes.size(); i++){



		}

    }

    private bool recursiveAttemptChangeCoin

}
