package com.nrigas.mastercard.requestBuilders;

import com.nrigas.mastercard.model.Merchant;
import com.nrigas.mastercard.service.Consent.DeleteConsentRequest;

import java.util.Optional;

public class DeleteConsentRequestBuilder extends RequestBuilder {

	private Merchant merchant;
	private String psuTppCustomerId;
	private String consentId;

	@Override
	public DeleteConsentRequestBuilder withAspsId(String aspsId) {
		super.withAspsId(aspsId);
		return this;
	}

	public DeleteConsentRequestBuilder withMerchant(String merchantId, String merchantName) {
		this.merchant = new Merchant(merchantId, merchantName);
		return this;
	}

	public DeleteConsentRequestBuilder withPsuTppCustomerId(String psuTppCustomerId) {
		this.psuTppCustomerId = psuTppCustomerId;
		return this;
	}

	public DeleteConsentRequestBuilder withConsentId(String consentId) {
		this.consentId = consentId;
		return this;
	}

	public DeleteConsentRequest build() {
		return new DeleteConsentRequest(
				this.aspsId,
				this.psuTppCustomerId,
				this.consentId,
				Optional.ofNullable(this.merchant)
		);
	}
}
