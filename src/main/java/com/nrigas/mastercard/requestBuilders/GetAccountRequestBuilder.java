package com.nrigas.mastercard.requestBuilders;

import com.nrigas.mastercard.request.GetAccountRequest;
import com.nrigas.mastercard.request.requestInfo.GetAccountRequestInfo;
import com.nrigas.mastercard.request.requestInfo.Merchant;

import java.util.UUID;

public class GetAccountRequestBuilder {

	public final GetAccountRequestInfo requestInfo;
	public String accountId;

	public GetAccountRequestBuilder() {
		this.requestInfo = new GetAccountRequestInfo();
	}

	public GetAccountRequestBuilder withAspspId(String aspspId) {
		this.requestInfo.aspspId = aspspId;
		return this;
	}

	public GetAccountRequestBuilder withPsuAgent(String psuAgent) {
		this.requestInfo.psuAgent = psuAgent;
		return this;
	};

	public GetAccountRequestBuilder withPsuIPAddress(String psuIPAddress) {
		this.requestInfo.psuIPAddress = psuIPAddress;
		return this;
	};

	public GetAccountRequestBuilder withIsLivePsuRequest(Boolean isLivePsuRequest) {
		this.requestInfo.isLivePsuRequest = isLivePsuRequest;
		return this;
	};

	public GetAccountRequestBuilder withPsuTppCustomerId(String psuTppCustomerId) {
		this.requestInfo.psuTppCustomerId = psuTppCustomerId;
		return this;
	};

	public GetAccountRequestBuilder withConsentId(String consentId) {
		this.requestInfo.consentId = consentId;
		return this;
	}

	public GetAccountRequestBuilder withMerchant(String merchantId, String merchantName) {
		this.requestInfo.merchant = new Merchant(merchantId, merchantName);
		return this;
	}

	public GetAccountRequestBuilder withAccountId(String accountId) {
		this.accountId = accountId;
		return this;
	};

	public GetAccountRequest build() {
		this.requestInfo.setxRequestId(UUID.randomUUID().toString());
		return new GetAccountRequest(requestInfo, this.accountId);
	}
}
