package com.example.hku.comp2396.assignment3;

public class CmdInsertCoin implements Command {

  @Override
  public String execute(VendingMachine v, String cmdPart) {
    var coinValue = Integer.valueOf(cmdPart);
    v.addCoinToCoinSlot(coinValue);
    return new StringBuilder().append("Inserted a $").append(coinValue).append(" coin. $")
        .append(v.coinSlot.getSum()).append(" in Total.").toString();
  }

}