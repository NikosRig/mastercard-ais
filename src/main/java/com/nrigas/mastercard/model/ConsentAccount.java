package com.nrigas.mastercard.model;

public class ConsentAccount {

	private final String id;
	private final String currency;

	public ConsentAccount(String id, String currency) {
		this.id = id;
		this.currency = currency;
	}

	public String getId() {
		return this.id;
	}

	public String getCurrency() {
		return currency;
	}
}
