package com.nrigas.mastercard.service.Consent;

import com.nrigas.mastercard.model.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

public class GetConsentRequest {

    public final String aspspId;
    public final String tppRedirectURI;
    public final ArrayList<ConsentPermission> permissions;
    public final LocalDateTime validUntilDateTime;
    public final Psu psu;
    public final Merchant merchant;
    public final Credentials credentials;
    public final ArrayList<ConsentAccount> consentAccountsList;

    public GetConsentRequest(
            String aspspId,
            String tppRedirectURI,
            ArrayList<ConsentPermission> permissions,
            ArrayList<ConsentAccount> consentAccountsList,
            Psu psu,
            Optional<Merchant> merchant,
            Optional<Credentials> credentials,
            Optional<LocalDateTime> validUntilDateTime
    ) {
        this.aspspId = aspspId;
        this.tppRedirectURI = tppRedirectURI;
        this.permissions = permissions;
        this.consentAccountsList = consentAccountsList;
        this.psu = psu;
        this.merchant = merchant.orElse(null);
        this.credentials = credentials.orElse(null);
        this.validUntilDateTime = validUntilDateTime.orElse(null);
    }
}
