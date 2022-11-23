package com.nrigas.mastercard.service.Consent.request;

import com.nrigas.mastercard.model.Merchant;
import com.nrigas.mastercard.model.Psu;

import java.util.Optional;

public class AuthConsentRequest {

	public final String aspspId;
	public final Psu psu;
	public final String authCode;
	public final Merchant merchant;

	public AuthConsentRequest(
			String aspspId,
			Psu psu,
			String authCode,
			Optional<Merchant> merchant
	) {
		this.aspspId = aspspId;
		this.psu = psu;
		this.authCode = authCode;
		this.merchant = merchant.orElse(null);
	}
}
