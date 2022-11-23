package com.nrigas.mastercard.request;

import com.nrigas.mastercard.model.AisConsentAccount;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

public class GetConsentRequest {

    public final String aspspId;
    public final Boolean isLivePsuRequest;
    public final String psuTppCustomerId;
    public final String psuIPAddress;
    public final String psuAgent;
    public final String tppRedirectURI;
    public final String merchantId;
    public final String merchantName;
    public final String iban;
    public final ArrayList<String> permissions;
    public final ArrayList<AisConsentAccount> accounts;
    public final LocalDateTime validUntilDateTime;

    public GetConsentRequest(
            String aspspId,
            String tppRedirectURI,
            ArrayList<String> permissions,
            ArrayList<AisConsentAccount> accounts,
            Optional<Boolean> isLivePsuRequest,
            Optional<String> psuTppCustomerId,
            Optional<String> psuIPAddress,
            Optional<String> psuAgent,
            Optional<String> merchantId,
            Optional<String> merchantName,
            Optional<String> iban,
            Optional<LocalDateTime> validUntilDateTime
    ) {
        this.aspspId = aspspId;
        this.tppRedirectURI = tppRedirectURI;
        this.permissions = permissions;
        this.accounts = accounts;
        this.isLivePsuRequest = isLivePsuRequest.orElse(true);
        this.psuTppCustomerId = psuTppCustomerId.orElse(null);
        this.psuIPAddress = psuIPAddress.orElse(null);
        this.psuAgent = psuAgent.orElse(null);
        this.merchantId = merchantId.orElse(null);
        this.merchantName = merchantName.orElse(null);
        this.iban = iban.orElse(null);
        this.validUntilDateTime = validUntilDateTime.orElse(null);
    }
}
