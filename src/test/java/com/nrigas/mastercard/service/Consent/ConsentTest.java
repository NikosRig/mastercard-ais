package com.nrigas.mastercard.service.Consent;

import com.nrigas.mastercard.TestCase;
import com.nrigas.mastercard.http.MastercardAisClient;
import com.nrigas.mastercard.requestBuilders.AuthConsentRequestBuilder;
import com.nrigas.mastercard.requestBuilders.GetConsentRequestBuilder;
import com.nrigas.mastercard.service.Consent.request.AuthConsentRequest;
import com.nrigas.mastercard.service.Consent.request.GetConsentRequest;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.net.http.HttpClient;
import java.net.http.HttpResponse;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;

public class ConsentTest extends TestCase {

    private Consent consent;
    private MastercardAisClient mastercardAisClient;

    @Before
    public void setUp() {
        HttpClient client = Mockito.mock(HttpClient.class);
        this.mastercardAisClient = Mockito.mock(MastercardAisClient.class);
        this.consent = new Consent(this.mastercardAisClient);
    }

    @Test
    public void getConsentShouldAddPsuAgentWhenExists() throws Exception {
        HttpResponse getConsentResponse = this.mockGetConsentResponse();
        Mockito.when(this.mastercardAisClient.postJson(any(), any())).thenReturn(getConsentResponse);

        GetConsentRequest getConsentRequestWithPsuAgent = new GetConsentRequestBuilder()
                .withAspsId("aspspId")
                .withTppRedirectUri("tppRedirectURI")
                .withPsu(true, "agent", null, null)
                .build();

        GetConsentRequest getConsentRequestWithoutPsuAgent = new GetConsentRequestBuilder()
                .withAspsId("aspspId")
                .withTppRedirectUri("tppRedirectURI")
                .withPsu(true, null, null, null)
                .build();

        this.consent.get(getConsentRequestWithPsuAgent);
        this.assertRequestInfoHas("psuAgent");

        this.consent.get(getConsentRequestWithoutPsuAgent);
        this.assertRequestInfoNotHas("psuAgent");
    }

    @Test
    public void getConsentShouldAddTppCustomerIdWhenExists() throws Exception {
        HttpResponse getConsentResponse = this.mockGetConsentResponse();
        Mockito.when(this.mastercardAisClient.postJson(any(), any())).thenReturn(getConsentResponse);

        GetConsentRequest getConsentRequestWithTppCustomerId = new GetConsentRequestBuilder()
                .withAspsId("aspspId")
                .withTppRedirectUri("tppRedirectURI")
                .withPsu(true, null, null, "tppCustomer")
                .build();

        GetConsentRequest getConsentRequestWithoutTppCustomerId = new GetConsentRequestBuilder()
                .withAspsId("aspspId")
                .withTppRedirectUri("tppRedirectURI")
                .withPsu(true, null, null, null)
                .build();

        this.consent.get(getConsentRequestWithTppCustomerId);
        this.assertRequestInfoHas("psuTppCustomerId");

        this.consent.get(getConsentRequestWithoutTppCustomerId);
        this.assertRequestInfoNotHas("psuTppCustomerId");
    }

    @Test
    public void getConsentShouldAddMerchantWhenExists() throws Exception {
        HttpResponse getConsentResponse = this.mockGetConsentResponse();
        Mockito.when(this.mastercardAisClient.postJson(any(), any())).thenReturn(getConsentResponse);

        GetConsentRequest getConsentRequestWithMerchant = new GetConsentRequestBuilder()
                .withAspsId("aspspId")
                .withTppRedirectUri("tppRedirectURI")
                .withPsu(true, null, null, null)
                .withMerchant("id", "name")
                .build();

        GetConsentRequest getConsentRequestWithoutMerchant = new GetConsentRequestBuilder()
                .withAspsId("aspspId")
                .withTppRedirectUri("tppRedirectURI")
                .withPsu(true, null, null, null)
                .build();

        this.consent.get(getConsentRequestWithMerchant);
        this.assertRequestInfoHas("merchant");

        this.consent.get(getConsentRequestWithoutMerchant);
        this.assertRequestInfoNotHas("merchant");
    }

    @Test
    public void getConsentShouldAddPsuIpAddressOnlyWhenExists() throws Exception {
        HttpResponse getConsentResponse = this.mockGetConsentResponse();
        Mockito.when(this.mastercardAisClient.postJson(any(), any())).thenReturn(getConsentResponse);

        GetConsentRequest getConsentRequestWithPsuIp = new GetConsentRequestBuilder()
                .withAspsId("aspspId")
                .withTppRedirectUri("tppRedirectURI")
                .withPsu(true, null, "127.0.0.1", null)
                .build();

        GetConsentRequest getConsentRequestWithoutPsuIp = new GetConsentRequestBuilder()
                .withAspsId("aspspId")
                .withTppRedirectUri("tppRedirectURI")
                .withPsu(true, null, null, null)
                .build();

        this.consent.get(getConsentRequestWithPsuIp);
        this.assertRequestInfoHas("psuIPAddress");

        this.consent.get(getConsentRequestWithoutPsuIp);
        this.assertRequestInfoNotHas("psuIPAddress");
    }

    @Test
    public void getConsentShouldAddMultipleAccounts() throws Exception {
        HttpResponse getConsentResponse = this.mockGetConsentResponse();
        Mockito.when(this.mastercardAisClient.postJson(any(), any())).thenReturn(getConsentResponse);

        GetConsentRequest request = new GetConsentRequestBuilder()
                .withAspsId("aspspId")
                .withTppRedirectUri("tppRedirectURI")
                .withPsu(true, null, "127.0.0.1", null)
                .addConsentAccount("ACCNUMBR1234567", "EUR")
                .addConsentAccount("ACCNUMBR1234567", "EUR")
                .build();

        this.consent.get(request);

        Mockito.verify(this.mastercardAisClient).postJson(any(), argThat(jsonBody -> {
            JSONArray accounts = new JSONObject(jsonBody).getJSONArray("accounts");
            return accounts.length() == 2;
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

    private HttpResponse mockGetConsentResponse() {
        String responseBody = "{\"originalRequestInfo\":{\"xRequestId\":\"e4dfcca5\"},\"aspspSCAApproach\":\"REDIRECT\",\"consentRequestId\":\"12345\",\"_links\":{\"scaRedirect\":\"https://openbanking.mastercard.eu/sandbox/mock/index.html?state=0418ae31-2e1e-49e3-a258-2c52c252a975&request=eyJjb25zZW50UmVxdWVzdElkIjoiMTIzNDUiLCJ0cHBSZWRpcmVjdFVSSSI6Imh0dHBzOi8vdHBwLW9iLmNvbS9jYWxsYmFjayJ9\"}}";
        return this.mockHttpResponse(responseBody, 200);
    }

    private HttpResponse mockAuthConsentResponse() {
        String responseBody = "{\"originalRequestInfo\":{\"xRequestId\":\"6fae488b-c8ac-44ec-a531-1dbb32e6b6f1\"},\"consentId\":\"GFiTpF3:EBy5xGqQMatk\",\"consentRequestId\":\"12345\"}";
        return this.mockHttpResponse(responseBody, 200);
    }

}
