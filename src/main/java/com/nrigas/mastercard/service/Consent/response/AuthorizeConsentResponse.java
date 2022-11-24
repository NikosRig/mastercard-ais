package com.nrigas.mastercard.service.Consent.response;

import com.nrigas.mastercard.model.OriginalRequestInfo;

public class AuthorizeConsentResponse {

	public final String consentId;
	public final String consentRequestId;
	public final OriginalRequestInfo originalRequestInfo;

	public AuthorizeConsentResponse(
			String consentId,
			String consentRequestId,
			OriginalRequestInfo originalRequestInfo
	) {
		this.consentId = consentId;
		this.consentRequestId = consentRequestId;
		this.originalRequestInfo = originalRequestInfo;
	}
}
