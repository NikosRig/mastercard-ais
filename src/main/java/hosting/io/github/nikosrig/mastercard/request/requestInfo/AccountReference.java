package hosting.io.github.nikosrig.mastercard.request.requestInfo;

import hosting.io.github.nikosrig.mastercard.model.AccountNumber;

public class AccountReference {

	public AccountNumber accountNumber;
	public final String currency;

	public AccountReference(AccountNumber accountNumber, String currency) {
		this.accountNumber = accountNumber;
		this.currency = currency;
	}
}
