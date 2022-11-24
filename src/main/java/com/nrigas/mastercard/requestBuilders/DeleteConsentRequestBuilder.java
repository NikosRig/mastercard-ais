package com.nrigas.mastercard.requestBuilders;

import com.nrigas.mastercard.model.Merchant;
import com.nrigas.mastercard.model.RequestInfo;
import com.nrigas.mastercard.request.DeleteConsentRequest;

public class DeleteConsentRequestBuilder extends RequestBuilder {

	private final RequestInfo requestInfo;
	private String consentId;

	public DeleteConsentRequestBuilder() {
		this.requestInfo = new RequestInfo();
	}

	@Override
	public DeleteConsentRequestBuilder withAspspId(String aspspId) {
		this.requestInfo.setAspspId(aspspId);
		return this;
	}

	public DeleteConsentRequestBuilder withMerchant(String merchantId, String merchantName) {
		this.requestInfo.setMerchant(new Merchant(merchantId, merchantName));
		return this;
	}

	public DeleteConsentRequestBuilder withPsuTppCustomerId(String psuTppCustomerId) {
		this.requestInfo.setPsuTppCustomerId(psuTppCustomerId);
		return this;
	}

	public DeleteConsentRequestBuilder withConsentId(String consentId) {
		this.consentId = consentId;
		return this;
	}

	public DeleteConsentRequest build() {
		return new DeleteConsentRequest(this.requestInfo, this.consentId);
	}
}
