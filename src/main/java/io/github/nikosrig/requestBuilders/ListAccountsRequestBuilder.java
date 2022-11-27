package io.github.nikosrig.requestBuilders;

import io.github.nikosrig.request.ListAccountsRequest;
import io.github.nikosrig.request.requestInfo.GetRawConsentRequestInfo;
import io.github.nikosrig.request.requestInfo.Merchant;
import io.github.nikosrig.request.requestInfo.RequestInfo;

public class ListAccountsRequestBuilder {

	public RequestInfo requestInfo;

	public ListAccountsRequestBuilder() {
		this.requestInfo = new GetRawConsentRequestInfo();
	}

	public ListAccountsRequestBuilder withConsentId(String consentId) {
		this.requestInfo.consentId = consentId;
		return this;
	}

	public ListAccountsRequestBuilder withMerchant(String merchantId, String merchantName) {
		this.requestInfo.merchant = new Merchant(merchantId, merchantName);
		return this;
	}

	public ListAccountsRequestBuilder withAspspId(String aspspId) {
		this.requestInfo.aspspId = aspspId;
		return this;
	}

	public ListAccountsRequestBuilder withPsuAgent(String psuAgent) {
		this.requestInfo.psuAgent = psuAgent;
		return this;
	};

	public ListAccountsRequestBuilder withPsuIPAddress(String psuIPAddress) {
		this.requestInfo.psuIPAddress = psuIPAddress;
		return this;
	};

	public ListAccountsRequestBuilder withIsLivePsuRequest(Boolean isLivePsuRequest) {
		this.requestInfo.isLivePsuRequest = isLivePsuRequest;
		return this;
	};

	public ListAccountsRequestBuilder withPsuTppCustomerId(String psuTppCustomerId) {
		this.requestInfo.psuTppCustomerId = psuTppCustomerId;
		return this;
	}

	public ListAccountsRequest build() {
		return new ListAccountsRequest(this);
	}
}
