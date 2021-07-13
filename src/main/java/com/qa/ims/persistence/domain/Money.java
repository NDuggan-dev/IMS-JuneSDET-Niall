package com.qa.ims.persistence.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;
import java.util.Locale;

public class Money {
	private static final Currency GBP = Currency.getInstance("GBP");
	private static final RoundingMode DEFAULT_ROUNDING = RoundingMode.HALF_EVEN;
	
	private final BigDecimal amount;
	private final Currency currency;
	
	public static Money pounds(BigDecimal amount) {
		return new Money(amount, GBP);
	}
	public static Money pounds(double amount) {
		BigDecimal amountBig = BigDecimal.valueOf(amount);
		return new Money(amountBig, GBP);
	}
	
	Money(BigDecimal amount, Currency currency){
		this(amount, currency, DEFAULT_ROUNDING);
	}
	
	Money(BigDecimal amount, Currency currency, RoundingMode rounding){
		this.currency = currency;
		this.amount = amount.setScale(currency.getDefaultFractionDigits(), rounding);
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public Currency getCurrency() {
		return currency;
	}
	
	@Override
	public String toString() {
		return getCurrency().getSymbol() + " " + getAmount();
	}
	
	public String toString(Locale locale) {
		return getCurrency().getSymbol(locale) + " " + getAmount();
	}
	
	

}
