package com.nrigas.mastercard.requestBuilders;

import com.nrigas.mastercard.model.GetAccountRequestInfo;
import com.nrigas.mastercard.model.Merchant;
import com.nrigas.mastercard.request.GetAccountRequest;

import java.util.UUID;

public class GetAccountRequestBuilder extends RequestBuilder {

	protected GetAccountRequestInfo requestInfo;
	private String accountId;

	@Override
	public void addAspspId(String aspspId) {
		this.requestInfo.setAspspId(aspspId);
	}

	@Override
	public void addPsu(
			Boolean isLivePsuRequest,
			String psuAgent,
			String psuIPAddress,
			String psuTppCustomerId
	) {
		this.requestInfo.setPsuTppCustomerId(psuTppCustomerId);
		this.requestInfo.setPsuIPAddress(psuIPAddress);
		this.requestInfo.setPsuAgent(psuAgent);
		this.requestInfo.setLivePsuRequest(isLivePsuRequest);
	}

	@Override
	public void addMerchant(String merchantId, String merchantName) {
		this.requestInfo.setMerchant(new Merchant(merchantId, merchantName));
	}

	public void addConsentId(String consentId) {
		this.requestInfo.setConsentId(consentId);
	}

	public void addAccountId(String accountId) {
		this.accountId = accountId;
	}

	public GetAccountRequest build() {
		this.requestInfo.setxRequestId(UUID.randomUUID().toString());
		return new GetAccountRequest(requestInfo, this.accountId);
	}
}
