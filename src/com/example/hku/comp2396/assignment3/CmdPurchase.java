package com.example.hku.comp2396.assignment3;

public class CmdPurchase implements Command {

  @Override
  public String execute(VendingMachine v, String cmdPart) {
    try {
      var opt = v.softDrinkSlots.stream()
          .filter(softDrinkSlot -> softDrinkSlot.name.equals(cmdPart))
          .findFirst();
      if (opt.isEmpty() || opt.get().quantity <= 0) {
        return String.format("Purchasing %s... Out of stock!", cmdPart);
      }
      var drink = opt.get();
      if (drink.price == v.coinSlot.getSum()) {
        v.coinChanger.addAll(v.coinSlot);
        v.coinSlot.reset();
        drink.quantity--;
        return String
            .format("Purchasing %s... Success! Paid $%d. No change.", drink.name, drink.price);
      }
      if (drink.price > v.coinSlot.getSum()) {
        return String
            .format("Purchasing %s... Insufficient amount! Inserted $%d but needs $%d.", drink.name,
                v.coinSlot.getSum(), drink.price);
      }
      if (drink.price < v.coinSlot.getSum()) {
        var change = CoinPack.attemptAmount(v.coinChanger, v.coinSlot.getSum() - drink.price);
        if (change == null) {
          return String.format("Purchasing %s... Insufficient change!", drink.name);
        }
        v.coinChanger.removeAll(change);
        v.coinChanger.addAll(v.coinSlot);
        var coinSlotSum = v.coinSlot.getSum();
        v.coinSlot.reset();
        return String.format("Purchasing %s... Success! Paid $%d. Change: %s.", drink.name,
            coinSlotSum, change.getCoinListString());
      }
    } catch (InvalidOperationException | CloneNotSupportedException e) {
      return String.format("Operation fail: %s", e.getMessage());
    }
    return "Operation fail";
  }
}
