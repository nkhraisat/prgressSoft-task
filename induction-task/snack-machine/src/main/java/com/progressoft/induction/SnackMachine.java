package com.progressoft.induction;

import java.math.BigDecimal;

public class SnackMachine {

	public static int DEFAULT_QUANTITY = 10;
	private Money currentMachineMoney;
	private Money currentTransactedMoney;

	public Money getCurrentMachineMoney() {
		return currentMachineMoney;
	}

	public void setCurrentMachineMoney(Money currentMachineMoney) {
		this.currentMachineMoney = currentMachineMoney;
	}

	public Money getCurrentTransactedMoney() {
		return currentTransactedMoney;
	}

	public void setCurrentTransactedMoney(Money currentTransactedMoney) {
		this.currentTransactedMoney = currentTransactedMoney;
	}

	private SnackType chewingGums = SnackType.CHEWING_GUM;
	private SnackType chocolates = SnackType.CHOCOLATE;
	private SnackType chips = SnackType.CHIPS;

	public SnackMachine() {
		currentMachineMoney = Money.ZERO;
		chewingGums.amountOfSnack = DEFAULT_QUANTITY;
		chocolates.amountOfSnack = DEFAULT_QUANTITY;
		chips.amountOfSnack = DEFAULT_QUANTITY;
	}

	public Money moneyInside() {
		return getCurrentMachineMoney();
	}

	public void insertMoney(Money insertedMoney) {
		if (getCurrentTransactedMoney() == null) {
			setCurrentTransactedMoney(new Money(BigDecimal.ZERO));
		}

		if (insertedMoney == null) {
			throw new IllegalArgumentException("Please insert any amount of money!");
		}

		if (!insertedMoney.equals(Money.QUARTER_DINAR) && !insertedMoney.equals(Money.HALF_DINAR)
				&& !insertedMoney.equals(Money.DINAR) && !insertedMoney.equals(Money.FIVE_DINAR)
				&& !insertedMoney.equals(Money.TEN_DINAR)) {
			throw new IllegalArgumentException("Please insert an acceptable amount of money!");
		}

		setCurrentTransactedMoney(getCurrentTransactedMoney().add(insertedMoney));
	}

	public Money moneyInTransaction() {
		return getCurrentTransactedMoney();
	}

	public SnackType chewingGums() {
		return this.chewingGums;
	}

	public SnackType chips() {
		return this.chips;
	}

	public SnackType chocolates() {
		return this.chocolates;
	}

	public Money buySnack(SnackType snackType) {

		if (getCurrentTransactedMoney() == null || getCurrentTransactedMoney().equals(BigDecimal.ZERO))
			throw new IllegalStateException("Insert money!");

		setCurrentMachineMoney(getCurrentMachineMoney().add(getCurrentTransactedMoney()));

		if (getCurrentTransactedMoney().getCurrMoney().compareTo(snackType.getPrice().getCurrMoney()) > 0)
			Money.change.setCurrMoney( currentTransactedMoney.getCurrMoney().subtract(snackType.getPrice().getCurrMoney()));
		if (getCurrentTransactedMoney().getCurrMoney().compareTo(snackType.getPrice().getCurrMoney()) == 0)
			Money.change = Money.ZERO;

		if (getCurrentTransactedMoney().getCurrMoney().compareTo(snackType.getPrice().getCurrMoney()) < 0)
			throw new IllegalStateException("Not enough money!");

		if (snackType.quantity() == 0)
			throw new IllegalStateException("No more items!");

		if (snackType == chewingGums) {
			chewingGums().subQuantity();
		} else if (snackType == chips) {
			chips().subQuantity();
		} else if (snackType == chocolates) {
			chocolates().subQuantity();
		}

		setCurrentTransactedMoney(Money.ZERO);
		return Money.change;
	}
}
