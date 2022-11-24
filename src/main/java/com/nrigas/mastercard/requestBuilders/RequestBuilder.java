package com.nrigas.mastercard.requestBuilders;

import com.nrigas.mastercard.model.Merchant;

abstract public class RequestBuilder {

	protected String aspspId;
	protected Merchant merchant;
	protected Boolean isLivePsuRequest;
	protected String psuAgent;
	protected String psuIpAddress;
	protected String psuTppCustomerId;

	protected void addPsu(
			Boolean isLivePsuRequest,
			String psuAgent,
			String psuIpAddress,
			String psuTppCustomerId
	) {
		this.isLivePsuRequest = isLivePsuRequest;
		this.psuAgent = psuAgent;
		this.psuIpAddress = psuIpAddress;
		this.psuTppCustomerId = psuTppCustomerId;
	};

	protected void addMerchant(String merchantId, String merchantName) {
		this.merchant = new Merchant(merchantId, merchantName);
	}

	protected void addAspspId(String aspspId) {
		this.aspspId = aspspId;
	}
}
