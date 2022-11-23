package com.nrigas.mastercard.service.Consent.response;

public class AuthorizeConsentResponse {

	public final String consentId;
	public final String consentRequestId;
	public final String xRequestId;

	public AuthorizeConsentResponse(
			String consentId,
			String consentRequestId,
			String xRequestId
	) {
		this.consentId = consentId;
		this.consentRequestId = consentRequestId;
		this.xRequestId = xRequestId;
	}
}
