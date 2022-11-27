package io.github.nikosrig;

import io.github.nikosrig.http.MastercardAisAuthUtil;
import io.github.nikosrig.http.MastercardAisClient;

import java.net.http.HttpClient;

public class MastercardAis {

    private final MastercardAisClient mastercardClient;

    public MastercardAis(MastercardAisClient mastercardClient) throws Exception {
        this.mastercardClient = mastercardClient;
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

    public Aspsps aspsps() {
        return new Aspsps(this.mastercardClient);
    }

    public StandingOrders standingOrders() {
        return new StandingOrders(this.mastercardClient);
    }

    public static class Builder {

        private String pkcs12FilePath;
        private String signingKeyAlias;
        private String signingKeyPassword;
        private String consumerKey;
        private Boolean isSandboxMode;

        public Builder withPkcs12FilePath(String pkcs12FilePath) {
            this.pkcs12FilePath = pkcs12FilePath;
            return this;
        }

        public Builder withSigningKeyAlias(String signingKeyAlias) {
            this.signingKeyAlias = signingKeyAlias;
            return this;
        }

        public Builder withSigningKeyPassword(String signingKeyPassword) {
            this.signingKeyPassword = signingKeyPassword;
            return this;
        }

        public Builder withConsumerKey(String consumerKey) {
            this.consumerKey = consumerKey;
            return this;
        }

        public Builder enableSandboxMode() {
            this.isSandboxMode = true;
            return this;
        }

        public MastercardAis build() throws Exception {

            this.validateParams();

            MastercardAisAuthUtil authUtil = new MastercardAisAuthUtil(
                    this.pkcs12FilePath,
                    this.signingKeyAlias,
                    this.signingKeyPassword,
                    this.consumerKey
            );
            MastercardAisClient client = new MastercardAisClient(
                    HttpClient.newHttpClient(),
                    authUtil,
                    this.isSandboxMode != null ? this.isSandboxMode : false
            );

            return new MastercardAis(client);
        }

        private void validateParams() throws Exception {

            if (this.consumerKey == null) {
                throw new Exception("consumerKey is required");
            }

            if (this.signingKeyAlias == null) {
                throw new Exception("signingKeyAlias is required");
            }

            if (this.signingKeyPassword == null) {
                throw new Exception("signingKeyPassword is required");
            }
        }
    }
}
