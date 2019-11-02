package com.example.hku.comp2396.assignment3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class CoinPack implements Cloneable {

  private HashMap<Integer, Integer> coins;

  public CoinPack() {
    coins = new HashMap<Integer, Integer>();
  }

  public String getCoinListString() {
    return this.getCoinList().stream().map(n -> "$" + n.toString())
        .collect(Collectors.joining(", "));
  }

  public List<Integer> getCoinList() {
    var list = this.getAvailableCoinTypes().stream().map(i ->
        Collections.nCopies(this.getCoinCountOfType(i), i))
        .reduce(new ArrayList<Integer>(), (integers, integers2) -> {
          integers.addAll(integers2);
          return integers;
        });
    Collections.reverse(list);
    return list;
  }

  // clone() provides a copy of the CoinPack that can be safely modified
  @SuppressWarnings("unchecked")
  public CoinPack clone() throws CloneNotSupportedException {
    CoinPack pack = (CoinPack) super.clone();
    pack.coins = (HashMap<Integer, Integer>) coins.clone();
    return pack;
  }

  public Integer getSum() {
    return coins.entrySet().stream().mapToInt(
        integerIntegerEntry -> integerIntegerEntry.getValue() * integerIntegerEntry.getKey()).sum();
  }

  public Integer getCoinTotalCount() {
    return coins.entrySet().stream().mapToInt(Entry::getValue)
        .sum();
  }

  public Integer getCoinCountOfType(Integer i) {
    return coins.getOrDefault(i, 0);
  }

  public boolean hasCoinType(Integer i) {
    return coins.containsKey(i);
  }

  public boolean addCoin(Integer i) {
    coins.put(i, coins.getOrDefault(i, 0) + 1);
    return true;
  }

  public boolean removeCoin(Integer i) {
    assert coins.getOrDefault(i, 1) > 0 : "coin value must not be less than 1 !";
    if (!coins.containsKey(i)) {
      return false;
    } else if (coins.get(i) > 1) {
      coins.put(i, coins.get(i) - 1);
    } else if (coins.get(i) == 1) {
      coins.remove(i);
    }
    return true;
  }

  public void addAll(CoinPack cp) {
    for (var entry : cp.coins.entrySet()) {
      coins.put(entry.getKey(), coins.getOrDefault(entry.getKey(), 0) + entry.getValue());
    }
  }

  public void removeAll(CoinPack cp) throws InvalidOperationException {
    for (var i : cp.getAvailableCoinTypes()) {
      for (int j = 0; j < cp.getCoinCountOfType(i); j++) {
        var success = this.removeCoin(i);
        if (!success) {
          throw new InvalidOperationException();
        }
      }
    }
  }

  public CoinPack cloneAndAddCoin(Integer i)
      throws InvalidOperationException, CloneNotSupportedException {
    var pack = this.clone();
    if (!pack.addCoin(i)) {
      throw new InvalidOperationException();
    }
    return pack;
  }

  public CoinPack cloneAndRemoveCoin(Integer i)
      throws InvalidOperationException, CloneNotSupportedException {
    var pack = this.clone();
    if (!pack.removeCoin(i)) {
      throw new InvalidOperationException();
    }
    return pack;
  }

  public CoinPack cloneAndAddAll(CoinPack cp) throws CloneNotSupportedException {
    var pack = this.clone();
    pack.addAll(cp);
    return pack;
  }

  public List<Integer> getAvailableCoinTypes() {
    var l = new ArrayList<Integer>(coins.keySet());
    l.sort(Comparator.reverseOrder());
    return l;
  }

  public boolean isEmpty() {
    return coins.isEmpty();
  }

  public static CoinPack attemptAmount(CoinPack cp, Integer amt)
      throws InvalidOperationException, CloneNotSupportedException {

    // No more coins
    if (cp.isEmpty()) {
      return null;
    }

    // The value of the least valued coin is already larger than amt
    if (cp.getAvailableCoinTypes().get(cp.getAvailableCoinTypes().size() - 1) > amt) {
      return null;
    }

    // Return the pack containing such coin if current CoinPack contains the exact coin of value amt
    for (var i : cp.getAvailableCoinTypes()) {
      if (i.equals(amt)) {
        return new CoinPack().cloneAndAddCoin(i);
      }
    }

    // Attempt to transverse down the tree by subtracting one coin from both the amount and the pack
    for (var i : cp.getAvailableCoinTypes().stream().filter(integer -> integer < amt).collect(
        Collectors.toList())) {
      var attempt = attemptAmount(cp.cloneAndRemoveCoin(i), amt - i);
      if (attempt != null) {
        assert attempt.getSum() == amt - i;
        // If such pack with sum as the required amount is found, add the current value to it and then pass it up
        return attempt.cloneAndAddCoin(i);
      }
    }

    return null;

  }

  public void reset() {
    coins = new HashMap<>();
  }

}

