package io.github.nikosrig;

import io.github.nikosrig.http.MastercardAisAuthUtil;
import io.github.nikosrig.http.MastercardAisClient;
import org.json.JSONObject;
import org.junit.Before;
import org.mockito.Mockito;

import java.net.http.HttpClient;
import java.net.http.HttpResponse;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;

public class TestCase {

    protected MastercardAisClient mastercardAisClient;

    @Before
    public void setUp() {
        this.mastercardAisClient = Mockito.mock(MastercardAisClient.class);
    }

    public void assertRequestInfoHas(String key) throws Exception {
        Mockito.verify(this.mastercardAisClient).postJson(any(), argThat(jsonBody -> {
            JSONObject requestInfo = new JSONObject(jsonBody).getJSONObject("requestInfo");
            return requestInfo.has(key);
        }));
    }

    public void assertRequestInfoNotHas(String key) throws Exception {
        Mockito.verify(this.mastercardAisClient).postJson(any(), argThat(jsonBody -> {
            JSONObject requestInfo = new JSONObject(jsonBody).getJSONObject("requestInfo");
            return !requestInfo.has(key);
        }));
    }

    public void assertRequestHas(String key) throws Exception {
        Mockito.verify(this.mastercardAisClient).postJson(any(), argThat(jsonBody -> {
            return new JSONObject(jsonBody).has(key);
        }));
    }

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

        return new MastercardAisClient(client, authUtil, true);
    }
}
