package hosting.io.github.nikosrig.mastercard.request;

import hosting.io.github.nikosrig.mastercard.request.requestInfo.ConsentAccount;
import hosting.io.github.nikosrig.mastercard.request.requestInfo.ConsentPermission;
import hosting.io.github.nikosrig.mastercard.request.requestInfo.GetConsentRequestInfo;
import hosting.io.github.nikosrig.mastercard.requestBuilders.GetConsentRequestBuilder;

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
