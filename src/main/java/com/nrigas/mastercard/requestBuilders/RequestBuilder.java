package com.nrigas.mastercard.requestBuilders;

import com.nrigas.mastercard.model.Merchant;
import com.nrigas.mastercard.model.RequestInfo;

abstract public class RequestBuilder {

	protected String aspspId;
	protected Merchant merchant;
	protected Boolean isLivePsuRequest;
	protected String psuAgent;
	protected String psuIpAddress;
	protected String psuTppCustomerId;
	protected RequestInfo requestInfo;

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
	};

	public void addMerchant(String merchantId, String merchantName) {
		this.requestInfo.setMerchant(new Merchant(merchantId, merchantName));
	}

	public void addAspspId(String aspspId) {
		this.requestInfo.setAspspId(aspspId);
	}
}
