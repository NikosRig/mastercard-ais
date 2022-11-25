package com.nrigas.mastercard.requestBuilders;

import com.nrigas.mastercard.request.ListStandingOrdersRequest;
import com.nrigas.mastercard.request.requestInfo.GetRawConsentRequestInfo;
import com.nrigas.mastercard.request.requestInfo.Merchant;
import com.nrigas.mastercard.request.requestInfo.RequestInfo;

public class ListStandingOrdersRequestBuilder {

	public RequestInfo requestInfo;
	public String accountId;
	public Integer limit;
	public String offset;

	public ListStandingOrdersRequestBuilder() {
		this.requestInfo = new GetRawConsentRequestInfo();
	}

	public ListStandingOrdersRequestBuilder withConsentId(String consentId) {
		this.requestInfo.consentId = consentId;
		return this;
	}

	public ListStandingOrdersRequestBuilder withMerchant(String merchantId, String merchantName) {
		this.requestInfo.merchant = new Merchant(merchantId, merchantName);
		return this;
	}

	public ListStandingOrdersRequestBuilder withAspspId(String aspspId) {
		this.requestInfo.aspspId = aspspId;
		return this;
	}

	public ListStandingOrdersRequestBuilder withPsuAgent(String psuAgent) {
		this.requestInfo.psuAgent = psuAgent;
		return this;
	};

	public ListStandingOrdersRequestBuilder withPsuIPAddress(String psuIPAddress) {
		this.requestInfo.psuIPAddress = psuIPAddress;
		return this;
	};

	public ListStandingOrdersRequestBuilder withIsLivePsuRequest(Boolean isLivePsuRequest) {
		this.requestInfo.isLivePsuRequest = isLivePsuRequest;
		return this;
	};

	public ListStandingOrdersRequestBuilder withPsuTppCustomerId(String psuTppCustomerId) {
		this.requestInfo.psuTppCustomerId = psuTppCustomerId;
		return this;
	}

	public ListStandingOrdersRequestBuilder withAccountId(String accountId) {
		this.accountId = accountId;
		return this;
	}

	public ListStandingOrdersRequestBuilder withLimit(Integer limit) {
		this.limit = limit;
		return this;
	}

	public ListStandingOrdersRequestBuilder withOffset(String offset) {
		this.offset = offset;
		return this;
	}

	public ListStandingOrdersRequest build() {
		return new ListStandingOrdersRequest(this);
	}
}
