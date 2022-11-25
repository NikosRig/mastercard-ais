package com.nrigas.mastercard.model;

public class AuthorizedConsent {

	public final String consentId;
	public final String consentRequestId;
	public final OriginalRequestInfo originalRequestInfo;

	public AuthorizedConsent(
			String consentId,
			String consentRequestId,
			OriginalRequestInfo originalRequestInfo
	) {
		this.consentId = consentId;
		this.consentRequestId = consentRequestId;
		this.originalRequestInfo = originalRequestInfo;
	}
}
