package com.nrigas.mastercard.request.requestInfo;

public class GetRawConsentRequestInfo extends RequestInfo {

	public String consentId;

	public GetRawConsentRequestInfo withConsentId(String consentId) {
		this.consentId = consentId;
		return this;
	}
}
