package com.nrigas.mastercard.requestBuilders;

import com.nrigas.mastercard.model.Merchant;
import com.nrigas.mastercard.model.RequestInfoImpl;
import com.nrigas.mastercard.request.DeleteConsentRequest;

public class DeleteConsentRequestBuilder extends RequestBuilder {

	protected RequestInfoImpl requestInfo;
	private String consentId;

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
