package com.nrigas.mastercard.request;

import com.nrigas.mastercard.request.requestInfo.RequestInfoImpl;

public class DeleteConsentRequest {

	public final RequestInfoImpl requestInfo;
	public final String consentId;

	public DeleteConsentRequest(RequestInfoImpl requestInfo, String consentId) {
		this.requestInfo = requestInfo;
		this.consentId = consentId;
	}
}
