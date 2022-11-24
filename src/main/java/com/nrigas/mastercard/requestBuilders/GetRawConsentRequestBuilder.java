package com.nrigas.mastercard.requestBuilders;

import com.nrigas.mastercard.request.GetRawConsentRequest;
import com.nrigas.mastercard.request.requestInfo.GetRawConsentRequestInfo;
import com.nrigas.mastercard.request.requestInfo.Merchant;

public class GetRawConsentRequestBuilder {

	private GetRawConsentRequestInfo requestInfo;

	public GetRawConsentRequestBuilder() {
		this.requestInfo = new GetRawConsentRequestInfo();
	}

	public GetRawConsentRequestBuilder withConsentId(String consentId) {
		this.requestInfo.consentId = consentId;
		return this;
	}

	public GetRawConsentRequestBuilder withMerchant(String merchantId, String merchantName) {
		this.requestInfo.merchant = new Merchant(merchantId, merchantName);
		return this;
	}

	public GetRawConsentRequestBuilder withAspspId(String aspspId) {
		this.requestInfo.aspspId = aspspId;
		return this;
	}

	public GetRawConsentRequestBuilder withPsuAgent(String psuAgent) {
		this.requestInfo.psuAgent = psuAgent;
		return this;
	};

	public GetRawConsentRequestBuilder withPsuIPAddress(String psuIPAddress) {
		this.requestInfo.psuIPAddress = psuIPAddress;
		return this;
	};

	public GetRawConsentRequestBuilder withIsLivePsuRequest(Boolean isLivePsuRequest) {
		this.requestInfo.isLivePsuRequest = isLivePsuRequest;
		return this;
	};

	public GetRawConsentRequest build() {
		return new GetRawConsentRequest(this.requestInfo);
	}
}
