package io.github.nikosrig.request.requestInfo;

import io.github.nikosrig.model.AccountNumber;

public class AccountReference {

	public AccountNumber accountNumber;
	public final String currency;

	public AccountReference(AccountNumber accountNumber, String currency) {
		this.accountNumber = accountNumber;
		this.currency = currency;
	}
}
