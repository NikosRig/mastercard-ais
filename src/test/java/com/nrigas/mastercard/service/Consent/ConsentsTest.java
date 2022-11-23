package com.nrigas.mastercard.service.Consent;

import com.nrigas.mastercard.TestCase;
import com.nrigas.mastercard.http.MastercardAisClient;
import com.nrigas.mastercard.model.Consent;
import com.nrigas.mastercard.requestBuilders.AuthConsentRequestBuilder;
import com.nrigas.mastercard.requestBuilders.ConsentRequestBuilder;
import com.nrigas.mastercard.service.Consent.request.AuthConsentRequest;
import com.nrigas.mastercard.service.Consent.request.ConsentRequest;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.net.http.HttpResponse;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;

public class ConsentsTest extends TestCase {

    private Consents consent;
    private MastercardAisClient mastercardAisClient;

    @Before
    public void setUp() {
        this.mastercardAisClient = Mockito.mock(MastercardAisClient.class);
        this.consent = new Consents(this.mastercardAisClient);
    }

    @Test
    public void testVerifyGetConsentPsuAgentIsOptional() throws Exception {
        this.mockGetConsentResponse();

        ConsentRequest request = new ConsentRequestBuilder()
                .withAspsId("aspspId")
                .withTppRedirectUri("tppRedirectURI")
                .withPsu(true, null, "127.0.0.1", "psuTppCustomerId")
                .build();
        this.consent.get(request);

        this.assertRequestInfoNotHas("psuAgent");
    }

    @Test
    public void testVerifyGetConsentPsuAgentWillBePassed() throws Exception {
        this.mockGetConsentResponse();

        ConsentRequest request = new ConsentRequestBuilder()
                .withAspsId("aspspId")
                .withTppRedirectUri("tppRedirectURI")
                .withPsu(true, "agent", null, null)
                .build();
        this.consent.get(request);

        this.assertRequestInfoHas("psuAgent");
    }

    @Test
    public void testVerifyGetConsentPsuTppCustomerIsOptional() throws Exception {
        this.mockGetConsentResponse();

        ConsentRequest request = new ConsentRequestBuilder()
                .withAspsId("aspspId")
                .withTppRedirectUri("tppRedirectURI")
                .withPsu(true, null, null, null)
                .build();
        this.consent.get(request);

        this.assertRequestInfoNotHas("psuTppCustomerId");
    }

    @Test
    public void testVerifyGetConsentPsuTppCustomerWillBePassed() throws Exception {
        this.mockGetConsentResponse();

        ConsentRequest request = new ConsentRequestBuilder()
                .withAspsId("aspspId")
                .withTppRedirectUri("tppRedirectURI")
                .withPsu(true, null, null, "psuTppCustomerId")
                .build();
        this.consent.get(request);

        this.assertRequestInfoHas("psuTppCustomerId");
    }

    @Test
    public void testVerifyGetConsent() throws Exception {

        this.mockGetConsentResponse();
        ConsentRequest request = new ConsentRequestBuilder()
                .withAspsId("aspspId")
                .withTppRedirectUri("tppRedirectURI")
                .withPsu(true, null, null, null)
                .withMerchant("id", "name")
                .build();
        Consent consent = this.consent.get(request);

        Assert.assertNotNull(consent.consentRequestId);
        Assert.assertNotNull(consent.scaRedirectUri);
        Assert.assertNotNull(consent.xRequestId);
    }

    @Test
    public void testVerifyGetConsentMerchantWillBePassed() throws Exception {
        this.mockGetConsentResponse();

        ConsentRequest request = new ConsentRequestBuilder()
                .withAspsId("aspspId")
                .withTppRedirectUri("tppRedirectURI")
                .withPsu(true, null, null, null)
                .withMerchant("id", "name")
                .build();
        this.consent.get(request);

        this.assertRequestInfoHas("merchant");
    }

    @Test
    public void testVerifyGetConsentMerchantIsOptional() throws Exception {
        this.mockGetConsentResponse();

        ConsentRequest request = new ConsentRequestBuilder()
                .withAspsId("aspspId")
                .withTppRedirectUri("tppRedirectURI")
                .withPsu(true, null, null, null)
                .build();
        this.consent.get(request);

        this.assertRequestInfoNotHas("merchant");
    }

    @Test
    public void testVerifyGetConsentPsuIpAddressWillBePassed() throws Exception {
        this.mockGetConsentResponse();

        ConsentRequest request = new ConsentRequestBuilder()
                .withAspsId("aspspId")
                .withTppRedirectUri("tppRedirectURI")
                .withPsu(true, null, "127.0.0.1", null)
                .build();
        this.consent.get(request);

        this.assertRequestInfoHas("psuIPAddress");
    }

    @Test
    public void testVerifyGetConsentPsuIpAddressIsOptional() throws Exception {
        this.mockGetConsentResponse();

        ConsentRequest request = new ConsentRequestBuilder()
                .withAspsId("aspspId")
                .withTppRedirectUri("tppRedirectURI")
                .withPsu(true, null, null, null)
                .build();
        this.consent.get(request);

        this.assertRequestInfoNotHas("psuIPAddress");
    }

    @Test
    public void testVerifyGetConsentMultipleAccountsCanBeAdded() throws Exception {
        this.mockGetConsentResponse();

        ConsentRequest request = new ConsentRequestBuilder()
                .withAspsId("aspspId")
                .withTppRedirectUri("tppRedirectURI")
                .withPsu(true, null, "127.0.0.1", null)
                .addConsentAccount("ACCNUMBR1234567", "EUR")
                .addConsentAccount("ACCNUMBR1234567", "EUR")
                .build();
        this.consent.get(request);

        Mockito.verify(this.mastercardAisClient).postJson(any(), argThat(jsonBody -> {
            return new JSONObject(jsonBody).getJSONArray("accounts").length() == 2;
        }));
    }

    @Test
    public void authConsentShouldAddPsuIpAddressOnlyWhenExists() throws Exception {
        HttpResponse authConsentResponse = this.mockAuthConsentResponse();
        Mockito.when(this.mastercardAisClient.postJson(any(), any())).thenReturn(authConsentResponse);

        AuthConsentRequest authConsentRequest = new AuthConsentRequestBuilder()
                .withAspsId("aspspId")
                .withAuthCode("code=xxxx")
                .withPsu(true, null, "127.0.0.1")
                .build();

        AuthConsentRequest authConsentRequestWithoutPsuIp = new AuthConsentRequestBuilder()
                .withAspsId("aspspId")
                .withAuthCode("code=xxxx")
                .withPsu(true, null, null)
                .build();

        this.consent.authorize(authConsentRequest);
        this.assertRequestInfoHas("psuIPAddress");

        this.consent.authorize(authConsentRequestWithoutPsuIp);
        this.assertRequestInfoNotHas("psuIPAddress");
    }

    private void assertRequestInfoHas(String key) throws Exception {
        Mockito.verify(this.mastercardAisClient).postJson(any(), argThat(jsonBody -> {
            JSONObject requestInfo = new JSONObject(jsonBody).getJSONObject("requestInfo");
            return requestInfo.has(key);
        }));
    }

    private void assertRequestInfoNotHas(String key) throws Exception {
        Mockito.verify(this.mastercardAisClient).postJson(any(), argThat(jsonBody -> {
            JSONObject requestInfo = new JSONObject(jsonBody).getJSONObject("requestInfo");
            return !requestInfo.has(key);
        }));
    }

    private HttpResponse mockFormatErrorResponse() {
        String responseBody = "{\"Errors\":{\"Error\":[{\"Source\":\"OBC\",\"ReasonCode\":\"FORMAT_ERROR\",\"Description\":\"[Path '/requestInfo/merchant'] Object instance has properties which are not allowed by the schema: [\\\"merchantName\\\"]\",\"Details\":\"path[0]=/requestInfo/merchant\"},{\"Source\":\"OBC\",\"ReasonCode\":\"FORMAT_ERROR\",\"Description\":\"[Path '/requestInfo/merchant'] Object has missing required properties ([\\\"name\\\"])\",\"Details\":\"path[0]=/requestInfo/merchant\"}]}}";
        return this.mockHttpResponse(responseBody, 400);
    }

    private void mockGetConsentResponse() throws Exception {
        String responseBody = "{\"originalRequestInfo\":{\"xRequestId\":\"e4dfcca5\"},\"aspspSCAApproach\":\"REDIRECT\",\"consentRequestId\":\"12345\",\"_links\":{\"scaRedirect\":\"https://openbanking.mastercard.eu/sandbox/mock/index.html?state=0418ae31-2e1e-49e3-a258-2c52c252a975&request=eyJjb25zZW50UmVxdWVzdElkIjoiMTIzNDUiLCJ0cHBSZWRpcmVjdFVSSSI6Imh0dHBzOi8vdHBwLW9iLmNvbS9jYWxsYmFjayJ9\"}}";
        HttpResponse response = this.mockHttpResponse(responseBody, 200);
        Mockito.when(this.mastercardAisClient.postJson(any(), any())).thenReturn(response);
    }

    private HttpResponse mockAuthConsentResponse() {
        String responseBody = "{\"originalRequestInfo\":{\"xRequestId\":\"6fae488b-c8ac-44ec-a531-1dbb32e6b6f1\"},\"consentId\":\"GFiTpF3:EBy5xGqQMatk\",\"consentRequestId\":\"12345\"}";
        return this.mockHttpResponse(responseBody, 200);
    }

}
