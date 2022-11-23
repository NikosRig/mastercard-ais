package com.nrigas.mastercard;

public class MastercardAisConfig {

    public final String pkcs12FilePath;
    public final String signingKeyAlias;
    public final String signingKeyPassword;
    public final String consumerKey;
    public final Boolean sandboxMode;

    public MastercardAisConfig(
        String pkcs12FilePath,
        String signingKeyAlias,
        String signingKeyPassword,
        String consumerKey,
        Boolean sandboxMode
    ) {
        this.pkcs12FilePath = pkcs12FilePath;
        this.signingKeyAlias = signingKeyAlias;
        this.signingKeyPassword = signingKeyPassword;
        this.consumerKey = consumerKey;
        this.sandboxMode = sandboxMode;
    }
}
