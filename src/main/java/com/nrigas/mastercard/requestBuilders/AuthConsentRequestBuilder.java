package com.nrigas.mastercard.requestBuilders;

import com.nrigas.mastercard.model.Merchant;
import com.nrigas.mastercard.service.Consent.request.AuthConsentRequest;

import java.util.Optional;

public class AuthConsentRequestBuilder extends RequestBuilder {

	private Merchant merchant;
	private String authCode;

	public AuthConsentRequestBuilder withPsu(
			Boolean isLivePsuRequest,
			String psuAgent,
			String psuIpAddress
	) {
		super.withPsu(isLivePsuRequest, psuAgent, psuIpAddress, null);
		return this;
	}

	@Override
	public AuthConsentRequestBuilder withAspsId(String aspsId) {
		super.withAspsId(aspsId);
		return this;
	}

	@Override
	public AuthConsentRequestBuilder withMerchant(String merchantId, String merchantName) {
		super.withMerchant(merchantId, merchantName);
		return this;
	}

	public AuthConsentRequestBuilder withAuthCode(String authCode) {
		this.authCode = authCode;
		return this;
	}

	public AuthConsentRequest build() {
		return new AuthConsentRequest(
				this.aspsId,
				this.psu,
				this.authCode,
				Optional.ofNullable(this.merchant)
		);
	}
}
