package com.nrigas.mastercard.request;

import com.nrigas.mastercard.request.requestInfo.RequestInfo;
import com.nrigas.mastercard.requestBuilders.DeleteConsentRequestBuilder;

public class DeleteConsentRequest {

	public final RequestInfo requestInfo;
	public final String consentId;

	public DeleteConsentRequest(DeleteConsentRequestBuilder builder) {
		this.requestInfo = builder.requestInfo;
		this.consentId = builder.consentId;
	}
}
