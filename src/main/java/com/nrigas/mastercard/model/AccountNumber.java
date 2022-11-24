package com.nrigas.mastercard.model;

public class AccountNumber {

	private final String identification;
	private final String schemeName;

	public AccountNumber(String identification, String schemeName) {
		this.identification = identification;
		this.schemeName = schemeName;
	}
}
