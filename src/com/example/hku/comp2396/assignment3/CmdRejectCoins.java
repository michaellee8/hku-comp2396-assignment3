package com.example.hku.comp2396.assignment3;

public class CmdRejectCoins implements Command {

  @Override
  public String execute(VendingMachine v, String cmdPart) {
    if (v.coinSlot.getSum() == 0) {
      return "Rejected no coin!";
    }
    var result = new StringBuilder().append("Rejected ").append(
        v.coinSlot.getCoinListString()
    )
        .append(". $").append(v.coinSlot.getSum()).append(" in Total.").toString();
    v.coinSlot = new CoinPack();
    return result;
  }
}
