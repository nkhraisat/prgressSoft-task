package com.progressoft.induction;

import java.math.BigDecimal;

import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Money {
    public static final Money ZERO = new Money(BigDecimal.ZERO);
    public static final Money QUARTER_DINAR = new Money(BigDecimal.valueOf(0.25));
    public static final Money HALF_DINAR = new Money(BigDecimal.valueOf(0.5));
    public static final Money DINAR = new Money(BigDecimal.valueOf(1.0));
    public static final Money FIVE_DINAR = new Money(BigDecimal.valueOf(5.0));
    public static final Money TEN_DINAR = new Money(BigDecimal.valueOf(10.0));
    public static Money change = new Money(BigDecimal.ZERO);

    private BigDecimal currMoney;
    
    public BigDecimal getCurrMoney() {
		return currMoney;
	}

	public void setCurrMoney(BigDecimal currMoney) {
		this.currMoney = currMoney;
	}

	public Money(BigDecimal newMoney) {
        if (newMoney.compareTo(BigDecimal.ZERO) < 0){
            throw new IllegalArgumentException("The amount of money can't be negative.");
        }
        currMoney = newMoney;
    }

    public Money add(Money secondAmount) {
        Money money = new Money(this.getCurrMoney().add(secondAmount.getCurrMoney()));
        return money;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if(obj == null || this.getClass().getName()!=obj.getClass().getName())
            return false;
        Money money=(Money) obj;
        return this.currMoney.compareTo(money.getCurrMoney())==0;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getCurrMoney())
                .toHashCode();
    }

    public boolean isLessThan(Money money) {
        if (money != null) {
            if (this.getCurrMoney().compareTo(money.getCurrMoney()) >= 0) {
                return false;
            }
            return true;
        }
        return false;
    }

    public Money subtract(Money secondAmount) {
        return new Money(this.getCurrMoney().subtract(secondAmount.getCurrMoney()));
    }
}

