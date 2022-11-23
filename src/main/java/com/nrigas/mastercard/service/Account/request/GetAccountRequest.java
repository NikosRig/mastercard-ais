package com.nrigas.mastercard.service.Account.request;

import com.nrigas.mastercard.model.Merchant;
import com.nrigas.mastercard.model.Psu;

import java.util.Optional;

public class GetAccountRequest {

	public final String aspspId;
	public final Psu psu;
	public final String consentId;
	public final String accountId;
	public final Merchant merchant;

	public GetAccountRequest(
			String aspspId,
			Psu psu,
			String consentId,
			String accountId,
			Optional<Merchant> merchant
	) {
		this.aspspId = aspspId;
		this.psu = psu;
		this.consentId = consentId;
		this.accountId = accountId;
		this.merchant = merchant.orElse(null);
	}
}
