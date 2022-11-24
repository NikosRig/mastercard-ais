package com.nrigas.mastercard.request.requestInfo;

public class Credentials {

	private final String iban;

	public Credentials(String iban) {
		this.iban = iban;
	}

	public String getIban() {
		return iban;
	}
}
