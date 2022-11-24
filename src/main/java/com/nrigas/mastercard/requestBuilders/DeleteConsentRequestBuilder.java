package com.nrigas.mastercard.requestBuilders;

import com.nrigas.mastercard.request.DeleteConsentRequest;
import com.nrigas.mastercard.request.requestInfo.Merchant;
import com.nrigas.mastercard.request.requestInfo.RequestInfoImpl;

public class DeleteConsentRequestBuilder {

	protected RequestInfoImpl requestInfo;
	private String consentId;

	public DeleteConsentRequestBuilder() {
		this.requestInfo = new RequestInfoImpl();
	}

	public DeleteConsentRequestBuilder withMerchant(String merchantId, String merchantName) {
		this.requestInfo.merchant = new Merchant(merchantId, merchantName);
		return this;
	}

	public DeleteConsentRequestBuilder withAspspId(String aspspId) {
		this.requestInfo.aspspId = aspspId;
		return this;
	}

	public DeleteConsentRequestBuilder withPsuTppCustomerId(String psuTppCustomerId) {
		this.requestInfo.psuTppCustomerId = psuTppCustomerId;
		return this;
	};

	public DeleteConsentRequestBuilder withConsentId(String consentId) {
		this.consentId = consentId;
		return this;
	}

	public DeleteConsentRequest build() {
		return new DeleteConsentRequest(this.requestInfo, this.consentId);
	}
}
