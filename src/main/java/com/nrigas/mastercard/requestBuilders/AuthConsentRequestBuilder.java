package com.nrigas.mastercard.requestBuilders;

import com.nrigas.mastercard.request.AuthConsentRequest;
import com.nrigas.mastercard.request.requestInfo.Merchant;
import com.nrigas.mastercard.request.requestInfo.RequestInfo;

public class AuthConsentRequestBuilder {

	protected RequestInfo requestInfo;
	private String authorization;

	public AuthConsentRequestBuilder() {
		this.requestInfo = new RequestInfo();
	}

	public AuthConsentRequestBuilder withMerchant(String merchantId, String merchantName) {
		this.requestInfo.merchant = new Merchant(merchantId, merchantName);
		return this;
	}

	public AuthConsentRequestBuilder withAspspId(String aspspId) {
		this.requestInfo.aspspId = aspspId;
		return this;
	}

	public AuthConsentRequestBuilder withPsuAgent(String psuAgent) {
		this.requestInfo.psuAgent = psuAgent;
		return this;
	};

	public AuthConsentRequestBuilder withPsuIPAddress(String psuIPAddress) {
		this.requestInfo.psuIPAddress = psuIPAddress;
		return this;
	};

	public AuthConsentRequestBuilder withIsLivePsuRequest(Boolean isLivePsuRequest) {
		this.requestInfo.isLivePsuRequest = isLivePsuRequest;
		return this;
	};

	public AuthConsentRequestBuilder withAuthorization(String authorization) {
		this.authorization = authorization;
		return this;
	};

	public AuthConsentRequest build() {
		return new AuthConsentRequest(
				this.requestInfo,
				this.authorization
		);
	}
}
