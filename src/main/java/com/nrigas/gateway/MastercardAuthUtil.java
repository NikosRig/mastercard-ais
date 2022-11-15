package com.nrigas.gateway;

import com.mastercard.developer.oauth.OAuth;
import com.mastercard.developer.utils.AuthenticationUtils;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.security.PrivateKey;

public class MastercardAuthUtil {

    private final PrivateKey signingKey;
    private final String consumerKey;

    public MastercardAuthUtil(
        final String pkcs12FilePath,
        final String signingKeyAlias,
        final String signingKeyPassword,
        final String consumerKey
    ) throws Exception {
        try {
            this.consumerKey = consumerKey;
             this.signingKey = AuthenticationUtils.loadSigningKey(
                 pkcs12FilePath,
                 signingKeyAlias,
                 signingKeyPassword
             );
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public String createAuthHeader(final URI uri, final String payload) {
        return OAuth.getAuthorizationHeader(
            uri,
    "POST",
            payload,
            StandardCharsets.UTF_8,
            this.consumerKey,
            this.signingKey
        );
    }
}
