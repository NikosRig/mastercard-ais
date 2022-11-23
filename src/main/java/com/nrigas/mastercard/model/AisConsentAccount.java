package com.nrigas.mastercard.model;

public class AisConsentAccount {

	private final String iban;
	private final String currency;

	public AisConsentAccount(String identification, String currency) {
		this.iban = identification;
		this.currency = currency;
	}

	public String getIban() {
		return iban;
	}

	public String getCurrency() {
		return currency;
	}
}
