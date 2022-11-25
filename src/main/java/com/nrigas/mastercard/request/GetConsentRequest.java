package com.nrigas.mastercard.request;

import com.nrigas.mastercard.request.requestInfo.ConsentAccount;
import com.nrigas.mastercard.request.requestInfo.ConsentPermission;
import com.nrigas.mastercard.request.requestInfo.GetConsentRequestInfo;
import com.nrigas.mastercard.requestBuilders.GetConsentRequestBuilder;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class GetConsentRequest {

    public ArrayList<ConsentPermission> permissions;
    public LocalDateTime validUntilDateTime;
    public ArrayList<ConsentAccount> accounts;
    public GetConsentRequestInfo requestInfo;

    public GetConsentRequest(GetConsentRequestBuilder builder) {
        this.requestInfo = builder.requestInfo;
        this.permissions = builder.permissions;
        this.accounts = builder.consentAccounts;
        this.validUntilDateTime = builder.validUntilDateTime;
    }
}
