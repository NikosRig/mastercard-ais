package io.github.nikosrig.request;

import io.github.nikosrig.request.requestInfo.ConsentAccount;
import io.github.nikosrig.request.requestInfo.ConsentPermission;
import io.github.nikosrig.request.requestInfo.GetConsentRequestInfo;
import io.github.nikosrig.requestBuilders.GetConsentRequestBuilder;

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
