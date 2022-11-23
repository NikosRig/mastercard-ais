package com.nrigas.mastercard.service.Consent;

import com.nrigas.mastercard.model.Merchant;

import java.util.Optional;

public class DeleteConsentRequest {

	public final String aspspId;
	public final Merchant merchant;
	public final String psuTppCustomerId;
	public final String consentId;

	public DeleteConsentRequest(
			String aspspId,
			String psuTppCustomerId,
			String consentId,
			Optional<Merchant> merchant
	) {
		this.aspspId = aspspId;
		this.psuTppCustomerId = psuTppCustomerId;
		this.consentId = consentId;
		this.merchant = merchant.orElse(null);
	}
}
