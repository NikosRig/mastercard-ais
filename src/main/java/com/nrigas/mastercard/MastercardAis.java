package com.nrigas.mastercard;

import com.nrigas.mastercard.http.MastercardAisAuthUtil;
import com.nrigas.mastercard.http.MastercardAisClient;
import com.nrigas.mastercard.service.Account.Account;
import com.nrigas.mastercard.service.Consent.Consents;

import java.net.http.HttpClient;

public class MastercardAis {

    private final MastercardAisClient mastercardClient;

    public MastercardAis(MastercardAisConfig config) throws Exception {
        MastercardAisAuthUtil authUtil = new MastercardAisAuthUtil(
                config.pkcs12FilePath,
                config.signingKeyAlias,
                config.signingKeyPassword,
                config.consumerKey
        );
        this.mastercardClient = new MastercardAisClient(
                HttpClient.newHttpClient(),
                authUtil,
                config.sandboxMode
        );
    }

    public Consents consents() {
        return new Consents(this.mastercardClient);
    }

    public Account account() {
        return new Account(this.mastercardClient);
    }
}
