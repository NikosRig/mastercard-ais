package com.nrigas.mastercard.response;

final public class GetConsentResponse {

    public final String consentRequestId;
    public final String scaRedirectUri;

    public GetConsentResponse(
            String consentRequestId,
            String scaRedirectUri
    ) {
        this.consentRequestId = consentRequestId;
        this.scaRedirectUri = scaRedirectUri;
    }
}
