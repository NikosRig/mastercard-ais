package com.nrigas.mastercard;

import com.nrigas.mastercard.gateway.MastercardGatewayConfig;
import com.nrigas.mastercard.http.MastercardAisAuthUtil;
import com.nrigas.mastercard.http.MastercardAisClient;
import org.mockito.Mockito;

import java.net.http.HttpClient;
import java.net.http.HttpResponse;

public class TestCase {

    protected HttpResponse mockHttpResponse(String responseBody, Integer statusCode) {
        HttpResponse httpResponse = Mockito.mock(HttpResponse.class);
        Mockito.when(httpResponse.statusCode()).thenReturn(statusCode);
        Mockito.when(httpResponse.body()).thenReturn(responseBody);

        return httpResponse;
    }

    protected MastercardAisClient makeMastercardAisClient(HttpClient client) throws Exception {
        MastercardAisAuthUtil authUtil = new MastercardAisAuthUtil(
                "./var/key.p12",
                "keyalias",
                "keystorepassword",
                "nowzQEeE32g6FnESTjKEM6bq2mECwDX2SdOqfL9zae61ec44!5dda6ce1d93d4e2392232d711c4afb3f0000000000000000"
        );

        MastercardGatewayConfig config = new MastercardGatewayConfig(
                "https://tpp-ob.com/callback",
                true
        );

        return new MastercardAisClient(client, authUtil, true);
    }
}
