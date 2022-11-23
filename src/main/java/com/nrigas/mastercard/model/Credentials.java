package com.nrigas.mastercard.model;

public class Credentials {

	private final String iban;

	public Credentials(String iban) {
		this.iban = iban;
	}

	public String getIban() {
		return iban;
	}
}
