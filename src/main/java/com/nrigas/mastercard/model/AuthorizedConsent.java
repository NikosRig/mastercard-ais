package com.nrigas.mastercard.model;

public class AuthorizedConsent {

	public final String consentId;
	public final String consentRequestId;
	public final OriginalRequestInfo originalRequestInfo;
	public final String signatureStatus;

	public AuthorizedConsent(
			String consentId,
			String consentRequestId,
			OriginalRequestInfo originalRequestInfo,
			String signatureStatus
	) {
		this.consentId = consentId;
		this.signatureStatus = signatureStatus;
		this.consentRequestId = consentRequestId;
		this.originalRequestInfo = originalRequestInfo;
	}
}
