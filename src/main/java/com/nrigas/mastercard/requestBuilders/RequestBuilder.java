package com.nrigas.mastercard.requestBuilders;

import com.nrigas.mastercard.model.Merchant;
import com.nrigas.mastercard.model.Psu;

abstract public class RequestBuilder {

	protected Psu psu;
	protected String aspsId;
	protected Merchant merchant;
	protected Boolean isLivePsuRequest;
	protected String psuAgent;
	protected String psuIpAddress;
	protected String psuTppCustomerId;

	protected RequestBuilder withPsu(
			Boolean isLivePsuRequest,
			String psuAgent,
			String psuIpAddress,
			String psuTppCustomerId
	) {
		this.isLivePsuRequest = isLivePsuRequest;
		this.psuAgent = psuAgent;
		this.psuIpAddress = psuIpAddress;
		this.psuTppCustomerId = psuTppCustomerId;

		return this;
	};

	protected RequestBuilder withMerchant(String merchantId, String merchantName) {
		this.merchant = new Merchant(merchantId, merchantName);
		return this;
	}

	protected RequestBuilder withAspspId(String aspsId) {
		this.aspsId = aspsId;
		return this;
	}
}
