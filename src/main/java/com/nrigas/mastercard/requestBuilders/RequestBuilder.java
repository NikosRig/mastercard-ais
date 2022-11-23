package com.nrigas.mastercard.requestBuilders;

import com.nrigas.mastercard.model.Merchant;
import com.nrigas.mastercard.model.Psu;

import java.util.Optional;

abstract public class RequestBuilder {

	protected Psu psu;
	protected String aspsId;
	protected Merchant merchant;

	protected RequestBuilder withPsu(
			Boolean isLivePsuRequest,
			String psuAgent,
			String psuIpAddress,
			String psuTppCustomerId
	) {
		this.psu = new Psu(
				isLivePsuRequest,
				Optional.ofNullable(psuIpAddress),
				Optional.ofNullable(psuAgent),
				Optional.ofNullable(psuTppCustomerId)
		);
		return this;
	};

	protected RequestBuilder withMerchant(String merchantId, String merchantName) {
		this.merchant = new Merchant(merchantId, merchantName);
		return this;
	}

	protected RequestBuilder withAspsId(String aspsId) {
		this.aspsId = aspsId;
		return this;
	}
}
