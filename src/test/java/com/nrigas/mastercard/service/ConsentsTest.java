package com.nrigas.mastercard.service;

import com.nrigas.mastercard.Consents;
import com.nrigas.mastercard.TestCase;
import com.nrigas.mastercard.http.MastercardAisClient;
import com.nrigas.mastercard.model.Consent;
import com.nrigas.mastercard.requestBuilders.AuthConsentRequestBuilder;
import com.nrigas.mastercard.requestBuilders.GetConsentRequestBuilder;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.net.http.HttpResponse;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;

public class ConsentsTest extends TestCase {

    private Consents consents;
    private MastercardAisClient mastercardAisClient;

    @Before
    public void setUp() {
        this.mastercardAisClient = Mockito.mock(MastercardAisClient.class);
        this.consents = new Consents(this.mastercardAisClient);
    }

    @Test
    public void testVerifyGetConsentPsuAgentIsOptional() throws Exception {
        this.mockGetConsentResponse();

        GetConsentRequestBuilder requestBuilder = new GetConsentRequestBuilder();
        requestBuilder.addAspspId("aspspId");
        requestBuilder.withTppRedirectURI("tppRedirectURI");
        requestBuilder.addPsu(true, null, "127.0.0.1", "psuTppCustomerId");
        this.consent.get(requestBuilder.build());

        this.assertRequestInfoNotHas("psuAgent");
    }

    @Test
    public void testVerifyGetConsentPsuAgentWillBePassed() throws Exception {
        this.mockGetConsentResponse();

        GetConsentRequestBuilder requestBuilder = new GetConsentRequestBuilder();
        requestBuilder.addAspspId("aspspId");
        requestBuilder.withTppRedirectURI("tppRedirectURI");
        requestBuilder.addPsu(true, "agent", null, null);
        this.consent.get(requestBuilder.build());

        this.assertRequestInfoHas("psuAgent");
    }

    @Test
    public void testVerifyGetConsentPsuTppCustomerIsOptional() throws Exception {
        this.mockGetConsentResponse();

        GetConsentRequestBuilder requestBuilder = new GetConsentRequestBuilder();
        requestBuilder.addAspspId("aspspId");
        requestBuilder.withTppRedirectURI("tppRedirectURI");
        requestBuilder  .addPsu(true, null, null, null);
        this.consent.get(requestBuilder.build());

        this.assertRequestInfoNotHas("psuTppCustomerId");
    }

    @Test
    public void testVerifyGetConsentPsuTppCustomerWillBePassed() throws Exception {
        this.mockGetConsentResponse();

        GetConsentRequestBuilder requestBuilder = new GetConsentRequestBuilder();
        requestBuilder.addAspspId("aspspId");
        requestBuilder.withTppRedirectURI("tppRedirectURI");
        requestBuilder.addPsu(true, null, null, "psuTppCustomerId");
        this.consent.get(requestBuilder.build());

        this.assertRequestInfoHas("psuTppCustomerId");
    }

    @Test
    public void testVerifyGetConsent() throws Exception {

        this.mockGetConsentResponse();
        GetConsentRequestBuilder requestBuilder = new GetConsentRequestBuilder();
        requestBuilder.addAspspId("aspspId");
        requestBuilder.withTppRedirectURI("tppRedirectURI");
        requestBuilder.addPsu(true, null, null, null);
        requestBuilder.addMerchant("id", "name");
        Consent consent = this.consent.get(requestBuilder.build());

        Assert.assertNotNull(consent.consentRequestId);
        Assert.assertNotNull(consent.scaRedirectUri);
        Assert.assertNotNull(consent.xRequestId);
    }

    @Test
    public void testVerifyGetConsentMerchantWillBePassed() throws Exception {
        this.mockGetConsentResponse();

        GetConsentRequestBuilder requestBuilder = new GetConsentRequestBuilder();
        requestBuilder.addAspspId("aspspId");
        requestBuilder.withTppRedirectURI("tppRedirectURI");
        requestBuilder.addPsu(true, null, null, null);
        requestBuilder.addMerchant("id", "name");
        this.consent.get(requestBuilder.build());

        this.assertRequestInfoHas("merchant");
    }

    @Test
    public void testVerifyGetConsentMerchantIsOptional() throws Exception {
        this.mockGetConsentResponse();

        GetConsentRequestBuilder requestBuilder = new GetConsentRequestBuilder();
        requestBuilder.addAspspId("aspspId");
        requestBuilder.withTppRedirectURI("tppRedirectURI");
        requestBuilder.addPsu(true, null, null, null);
        this.consent.get(requestBuilder.build());

        this.assertRequestInfoNotHas("merchant");
    }

    @Test
    public void testVerifyGetConsentPsuIpAddressWillBePassed() throws Exception {
        this.mockGetConsentResponse();

        GetConsentRequestBuilder requestBuilder = new GetConsentRequestBuilder();
        requestBuilder.addAspspId("aspspId");
        requestBuilder.withTppRedirectURI("tppRedirectURI");
        requestBuilder.addPsu(true, null, "127.0.0.1", null);
        this.consent.get(requestBuilder.build());

        this.assertRequestInfoHas("psuIPAddress");
    }

    @Test
    public void testVerifyGetConsentPsuIpAddressIsOptional() throws Exception {
        this.mockGetConsentResponse();

        GetConsentRequestBuilder requestBuilder = new GetConsentRequestBuilder();
        requestBuilder.addAspspId("aspspId");
        requestBuilder.withTppRedirectURI("tppRedirectURI");
        requestBuilder.addPsu(true, null, null, null);
        this.consent.get(requestBuilder.build());

        this.assertRequestInfoNotHas("psuIPAddress");
    }

    @Test
    public void testVerifyGetConsentMultipleAccountsCanBeAdded() throws Exception {
        this.mockGetConsentResponse();

        GetConsentRequestBuilder requestBuilder = new GetConsentRequestBuilder();
        requestBuilder.addAspspId("aspspId");
        requestBuilder.withTppRedirectURI("tppRedirectURI");
        requestBuilder.addPsu(true, null, "127.0.0.1", null);
        requestBuilder.addAccount("ACCNUMBR1234567", "EUR", "IBAN");
        requestBuilder.addAccount("ACCNUMBR1234567", "EUR", "IBAN");
        this.consent.get(requestBuilder.build());

        Mockito.verify(this.mastercardAisClient).postJson(any(), argThat(jsonBody -> {
            return new JSONObject(jsonBody).getJSONArray("accounts").length() == 2;
        }));
    }

    @Test
    public void authConsentShouldAddPsuIpAddressWhenExists() throws Exception {
        HttpResponse authConsentResponse = this.mockAuthConsentResponse();
        Mockito.when(this.mastercardAisClient.postJson(any(), any())).thenReturn(authConsentResponse);

        AuthConsentRequestBuilder requestBuilder = new AuthConsentRequestBuilder();
        requestBuilder.addAspspId("aspspId");
        requestBuilder.addAspspId("code=xxxx");
        requestBuilder.addPsu(true, null, "127.0.0.1");
        requestBuilder.addAuthorization("authorization");
        this.consent.authorize(requestBuilder.build());

        this.assertRequestInfoHas("psuIPAddress");
    }

    @Test
    public void authConsentShouldNotAddPsuIpAddressWhenNotExists() throws Exception {
        HttpResponse authConsentResponse = this.mockAuthConsentResponse();
        Mockito.when(this.mastercardAisClient.postJson(any(), any())).thenReturn(authConsentResponse);

        AuthConsentRequestBuilder requestBuilder = new AuthConsentRequestBuilder();
        requestBuilder.addAspspId("aspspId");
        requestBuilder .addAuthorization("code=xxxx");
        requestBuilder.addPsu(true, null, null);
        this.consent.authorize(requestBuilder.build());

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
