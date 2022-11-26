package hosting.io.github.nikosrig.mastercard.http;

import com.mastercard.developer.oauth.OAuth;
import com.mastercard.developer.utils.AuthenticationUtils;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.security.PrivateKey;

public class MastercardAisAuthUtil {

    private final PrivateKey signingKey;
    private final String consumerKey;

    public MastercardAisAuthUtil(
        String pkcs12FilePath,
        String signingKeyAlias,
        String signingKeyPassword,
        String consumerKey
    ) throws Exception {
        this.consumerKey = consumerKey;
         this.signingKey = AuthenticationUtils.loadSigningKey(
             pkcs12FilePath,
             signingKeyAlias,
             signingKeyPassword
         );
    }

    public String createAuthHeader(URI uri, String payload) {
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
