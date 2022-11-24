package com.nrigas.mastercard.requestBuilders;

import com.nrigas.mastercard.model.Merchant;
import com.nrigas.mastercard.model.RequestInfo;
import com.nrigas.mastercard.request.AuthConsentRequest;

public class AuthConsentRequestBuilder extends RequestBuilder {

	private final RequestInfo requestInfo;
	private String authorization;

	public AuthConsentRequestBuilder() {
		this.requestInfo = new RequestInfo();
	}

	public AuthConsentRequestBuilder withPsu(
			Boolean isLivePsuRequest,
			String psuAgent,
			String psuIPAddress
	) {
		this.requestInfo.setPsuTppCustomerId(psuTppCustomerId);
		this.requestInfo.setPsuIPAddress(psuIPAddress);
		this.requestInfo.setPsuAgent(psuAgent);
		this.requestInfo.setLivePsuRequest(isLivePsuRequest);

		return this;
	}

	@Override
	public AuthConsentRequestBuilder withAspspId(String aspspId) {
		this.requestInfo.setAspspId(aspspId);
		return this;
	}

	@Override
	public AuthConsentRequestBuilder withMerchant(String merchantId, String merchantName) {
		this.requestInfo.setMerchant(new Merchant(merchantId, merchantName));
		return this;
	}

	public AuthConsentRequestBuilder withAuthorization(String authorization) {
		this.authorization = authorization;
		return this;
	}

	public AuthConsentRequest build() {
		return new AuthConsentRequest(
				this.requestInfo,
				this.authorization
		);
	}
}
