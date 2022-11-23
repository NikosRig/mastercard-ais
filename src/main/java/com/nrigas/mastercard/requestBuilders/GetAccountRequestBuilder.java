package com.nrigas.mastercard.requestBuilders;

import com.nrigas.mastercard.service.Account.request.GetAccountRequest;

import java.util.Optional;

public class GetAccountRequestBuilder extends RequestBuilder {

	private String consentId;
	private String accountId;

	@Override
	public GetAccountRequestBuilder withAspsId(String aspsId) {
		super.withAspsId(aspsId);
		return this;
	}

	@Override
	public GetAccountRequestBuilder withPsu(
			Boolean isLivePsuRequest,
			String psuAgent,
			String psuIpAddress,
			String psuTppCustomerId
	) {
		super.withPsu(isLivePsuRequest, psuAgent, psuIpAddress, psuTppCustomerId);
		return this;
	}

	@Override
	public GetAccountRequestBuilder withMerchant(String merchantId, String merchantName) {
		super.withMerchant(merchantId, merchantName);
		return this;
	}

	public GetAccountRequestBuilder withConsentId(String consentId) {
		this.consentId = consentId;
		return this;
	}

	public GetAccountRequestBuilder withAccountId(String accountId) {
		this.accountId = accountId;
		return this;
	}

	public GetAccountRequest build() {
		return new GetAccountRequest(
				this.aspsId,
				this.psu,
				this.consentId,
				this.accountId,
				Optional.ofNullable(this.merchant)
		);
	}
}
