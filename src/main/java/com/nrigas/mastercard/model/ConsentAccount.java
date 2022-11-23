package com.nrigas.mastercard.model;

public class ConsentAccount {

	private final String iban;
	private final String currency;

	public ConsentAccount(String iban, String currency) {
		this.iban = iban;
		this.currency = currency;
	}

	public String getIban() {
		return iban;
	}

	public String getCurrency() {
		return currency;
	}
}
