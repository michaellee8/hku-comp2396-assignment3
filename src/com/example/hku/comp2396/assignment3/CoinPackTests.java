package com.example.hku.comp2396.assignment3;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CoinPackTests {

  @Test
  public void basic() throws InvalidOperationException, CloneNotSupportedException {
    var pack = new CoinPack();
    pack.addCoin(3);
    pack.addCoin(2);
    pack.addCoin(1);
    pack.addCoin(3);
    pack.addCoin(1);
    pack.addCoin(2);
    var result = CoinPack.attemptAmount(pack, 5);
    Assertions.assertNotNull(result);
    Assertions.assertEquals(result.getCoinCountOfType(3), (Integer) 1);
    Assertions.assertEquals(result.getCoinCountOfType(2), (Integer) 1);
  }

  @Test
  public void advanced() throws InvalidOperationException, CloneNotSupportedException {
    new AdvancedTestCase(new int[]{1, 1, 2, 3}, 3, new int[]{3, 1}, true).runTest();
    new AdvancedTestCase(new int[]{2, 2}, 3, new int[]{}, false).runTest();
    new AdvancedTestCase(new int[]{2, 4, 2}, 4, new int[]{4, 1}, true).runTest();
    new AdvancedTestCase(new int[]{500}, 10, new int[]{}, false).runTest();
    new AdvancedTestCase(new int[]{2, 1, 10, 5, 5}, 8, new int[]{5, 1, 2, 1, 1, 1}, true).runTest();
    new AdvancedTestCase(new int[]{2, 1, 10, 5, 5}, 9, new int[]{}, false).runTest();
  }

}

class AdvancedTestCase {

  public List<Integer> initialCoins;
  public Integer amt;
  public Map<Integer, Integer> expectedResult;
  public boolean shouldSuccess;

  public AdvancedTestCase(int[] initialCoins, int amt, int[] expectedResult,
      boolean shouldSuccess) {
    if (expectedResult.length % 2 != 0) {
      throw new IllegalArgumentException();
    }
    this.initialCoins = new ArrayList<>();
    for (var i : initialCoins) {
      this.initialCoins.add(i);
    }
    this.amt = (Integer) amt;
    this.expectedResult = new HashMap<>();
    for (int i = 0; i < expectedResult.length; i += 2) {
      this.expectedResult.put(expectedResult[i], expectedResult[i + 1]);
    }
    this.shouldSuccess = shouldSuccess;
  }

  public void runTest() throws InvalidOperationException, CloneNotSupportedException {
    var pack = new CoinPack();
    for (var i : initialCoins) {
      pack.addCoin(i);
    }
    var result = CoinPack.attemptAmount(pack, amt);
    if (!shouldSuccess) {
      Assertions.assertNull(result);
    } else {
      Assertions.assertNotNull(result);
      Assertions.assertEquals(result.getAvailableCoinTypes().size(), expectedResult.size());
      for (var ent : expectedResult.entrySet()) {
        Assertions.assertEquals(ent.getValue(), result.getCoinCountOfType(ent.getKey()));
      }
    }
  }
}