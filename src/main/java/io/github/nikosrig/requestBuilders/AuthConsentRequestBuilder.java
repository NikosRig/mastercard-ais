package io.github.nikosrig.requestBuilders;

import io.github.nikosrig.request.AuthConsentRequest;
import io.github.nikosrig.request.requestInfo.Merchant;
import io.github.nikosrig.request.requestInfo.RequestInfo;

public class AuthConsentRequestBuilder {

	public RequestInfo requestInfo;
	public String authorization;

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
		return new AuthConsentRequest(this);
	}
}
