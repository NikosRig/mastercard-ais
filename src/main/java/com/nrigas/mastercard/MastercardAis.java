package com.nrigas.mastercard;

import com.nrigas.mastercard.http.MastercardAisAuthUtil;
import com.nrigas.mastercard.http.MastercardAisClient;

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

    public Accounts accounts() {
        return new Accounts(this.mastercardClient);
    }

    public Transactions transactions() {
        return new Transactions(this.mastercardClient);
    }

    public StandingOrders standingOrders() {
        return new StandingOrders(this.mastercardClient);
    }
}
