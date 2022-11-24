package com.nrigas.mastercard.model;

public class AccountReference {

	public AccountNumber accountNumber;
	public final String currency;

	public AccountReference(AccountNumber accountNumber, String currency) {
		this.accountNumber = accountNumber;
		this.currency = currency;
	}
}
