package io.github.nikosrig.requestBuilders;

import io.github.nikosrig.request.GetAccountRequest;
import io.github.nikosrig.request.requestInfo.GetAccountRequestInfo;
import io.github.nikosrig.request.requestInfo.Merchant;

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
		return new GetAccountRequest(this);
	}
}
