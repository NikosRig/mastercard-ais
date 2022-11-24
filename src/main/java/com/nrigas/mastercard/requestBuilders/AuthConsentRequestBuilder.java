package com.nrigas.mastercard.requestBuilders;

import com.nrigas.mastercard.model.Merchant;
import com.nrigas.mastercard.model.RequestInfoImpl;
import com.nrigas.mastercard.request.AuthConsentRequest;

public class AuthConsentRequestBuilder extends RequestBuilder {

	protected RequestInfoImpl requestInfo;
	private String authorization;

	public void addPsu(
			Boolean isLivePsuRequest,
			String psuAgent,
			String psuIPAddress
	) {
		this.requestInfo.setPsuIPAddress(psuIPAddress);
		this.requestInfo.setPsuAgent(psuAgent);
		this.requestInfo.setLivePsuRequest(isLivePsuRequest);

	}

	@Override
	public void addAspspId(String aspspId) {
		this.requestInfo.setAspspId(aspspId);
	}

	@Override
	public void addMerchant(String merchantId, String merchantName) {
		this.requestInfo.setMerchant(new Merchant(merchantId, merchantName));
	}

	public void addAuthorization(String authorization) {
		this.authorization = authorization;
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

	public AuthConsentRequest build() {
		return new AuthConsentRequest(
				this.requestInfo,
				this.authorization
		);
	}
}
