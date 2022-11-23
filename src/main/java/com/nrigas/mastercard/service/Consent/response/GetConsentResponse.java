package com.nrigas.mastercard.service.Consent.response;

final public class GetConsentResponse {

    public final String consentRequestId;
    public final String scaRedirectUri;
    public final String xRequestId;

    public GetConsentResponse(
            String consentRequestId,
            String scaRedirectUri,
            String xRequestId
    ) {
        this.consentRequestId = consentRequestId;
        this.scaRedirectUri = scaRedirectUri;
        this.xRequestId = xRequestId;
    }
}
