package com.progressoft.induction;


public enum SnackType {
    CHEWING_GUM(0, Money.HALF_DINAR), CHIPS(0, Money.DINAR), CHOCOLATE(0, Money.DINAR.add(Money.DINAR));

    int amountOfSnack;

    final Money price;
    SnackType(int q, Money p) {
        this.amountOfSnack = q;
        this.price = p;
    }

    public int quantity() {
        return this.amountOfSnack;
    }

    public Money getPrice(){
        return price;
    }

    public void subQuantity() {
        this.amountOfSnack -=1;
    }
}
