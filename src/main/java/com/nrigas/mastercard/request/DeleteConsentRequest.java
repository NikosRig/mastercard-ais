package com.nrigas.mastercard.request;

import com.nrigas.mastercard.model.RequestInfo;

public class DeleteConsentRequest {

	public final RequestInfo requestInfo;
	public final String consentId;

	public DeleteConsentRequest(RequestInfo requestInfo, String consentId) {
		this.requestInfo = requestInfo;
		this.consentId = consentId;
	}
}
