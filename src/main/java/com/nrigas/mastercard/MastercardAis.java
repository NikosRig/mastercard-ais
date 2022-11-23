package com.nrigas.mastercard;

import com.nrigas.mastercard.http.MastercardAisAuthUtil;
import com.nrigas.mastercard.http.MastercardAisClient;
import com.nrigas.mastercard.service.Consent;

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

    public Consent consent() {
        return new Consent(this.mastercardClient);
    }
}
