package com.nrigas.mastercard.request.requestInfo;

import com.nrigas.mastercard.model.AccountNumber;

public class AccountReference {

	public AccountNumber accountNumber;
	public final String currency;

	public AccountReference(AccountNumber accountNumber, String currency) {
		this.accountNumber = accountNumber;
		this.currency = currency;
	}
}
