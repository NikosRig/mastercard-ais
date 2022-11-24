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
	public void addAspspId(String aspspId) {
		this.requestInfo.setAspspId(aspspId);
	}

	public void addMerchant(String merchantId, String merchantName) {
		this.requestInfo.setMerchant(new Merchant(merchantId, merchantName));
	}

	public void addPsuTppCustomerId(String psuTppCustomerId) {
		this.requestInfo.setPsuTppCustomerId(psuTppCustomerId);
	}

	public void addConsentId(String consentId) {
		this.consentId = consentId;
	}

	public DeleteConsentRequest build() {
		return new DeleteConsentRequest(this.requestInfo, this.consentId);
	}
}
