package com.nrigas.mastercard.request;

import com.nrigas.mastercard.model.ConsentAccount;
import com.nrigas.mastercard.model.ConsentPermission;
import com.nrigas.mastercard.model.GetConsentRequestInfo;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class GetConsentRequest {

    public ArrayList<ConsentPermission> permissions;
    public LocalDateTime validUntilDateTime;
    public ArrayList<ConsentAccount> accounts;
    public GetConsentRequestInfo requestInfo;

    public GetConsentRequest(
            GetConsentRequestInfo requestInfo,
            LocalDateTime validUntilDateTime,
            ArrayList<ConsentPermission> permissions,
            ArrayList<ConsentAccount> accounts
    ) {
        this.requestInfo = requestInfo;
        this.permissions = permissions;
        this.accounts = accounts;
        this.validUntilDateTime = validUntilDateTime;
    }
}
