package com.nrigas.mastercard.requestBuilders;

import com.nrigas.mastercard.model.Merchant;

public abstract class AbstractRequestBuilder {

	public Merchant merchant;

	public AbstractRequestBuilder withMerchant(String merchantId, String merchantName) {
		this.merchant = new Merchant(merchantId, merchantName);
		return this;
	}
}
