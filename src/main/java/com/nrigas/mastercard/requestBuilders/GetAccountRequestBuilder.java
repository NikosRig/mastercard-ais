package com.nrigas.mastercard.requestBuilders;

import com.nrigas.mastercard.model.GetAccountRequestInfo;
import com.nrigas.mastercard.model.Merchant;
import com.nrigas.mastercard.request.GetAccountRequest;

import java.util.UUID;

public class GetAccountRequestBuilder extends RequestBuilder {

	private final GetAccountRequestInfo requestInfo;
	private String accountId;

	public GetAccountRequestBuilder() {
		this.requestInfo = new GetAccountRequestInfo();
	}

	@Override
	public GetAccountRequestBuilder withAspspId(String aspspId) {
		this.requestInfo.setAspspId(aspspId);
		return this;
	}

	@Override
	public GetAccountRequestBuilder withPsu(
			Boolean isLivePsuRequest,
			String psuAgent,
			String psuIPAddress,
			String psuTppCustomerId
	) {
		this.requestInfo.setPsuTppCustomerId(psuTppCustomerId);
		this.requestInfo.setPsuIPAddress(psuIPAddress);
		this.requestInfo.setPsuAgent(psuAgent);
		this.requestInfo.setLivePsuRequest(isLivePsuRequest);

		return this;
	}

	@Override
	public GetAccountRequestBuilder withMerchant(String merchantId, String merchantName) {
		this.requestInfo.setMerchant(new Merchant(merchantId, merchantName));
		return this;
	}

	public GetAccountRequestBuilder withConsentId(String consentId) {
		this.requestInfo.setConsentId(consentId);
		return this;
	}

	public GetAccountRequestBuilder withAccountId(String accountId) {
		this.accountId = accountId;
		return this;
	}

	public GetAccountRequest build() {
		this.requestInfo.setxRequestId(UUID.randomUUID().toString());
		return new GetAccountRequest(requestInfo, this.accountId);
	}
}
