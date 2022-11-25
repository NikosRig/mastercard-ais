package com.nrigas.mastercard;

import com.nrigas.mastercard.model.Consent;
import com.nrigas.mastercard.request.AuthConsentRequest;
import com.nrigas.mastercard.request.GetConsentRequest;
import com.nrigas.mastercard.request.requestInfo.ConsentPermission;
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

    @Before
    public void setUp() {
        super.setUp();
        this.consents = new Consents(this.mastercardAisClient);
    }

    @Test
    public void testVerifyGetConsentPsuAgentIsOptional() throws Exception {
        this.mockGetConsentResponse();

        GetConsentRequest request = new GetConsentRequestBuilder().build();
        this.consents.get(request);

        this.assertRequestInfoNotHas("psuAgent");
    }

    @Test
    public void testVerifyGetConsentPsuAgentWillBePassed() throws Exception {
        this.mockGetConsentResponse();

        GetConsentRequest request = new GetConsentRequestBuilder()
                .withPsuAgent("PostmanRuntime/7.20.1")
                .build();
        this.consents.get(request);

        this.assertRequestInfoHas("psuAgent");
    }

    @Test
    public void testVerifyGetConsentPsuTppCustomerIsOptional() throws Exception {
        this.mockGetConsentResponse();

        GetConsentRequest request = new GetConsentRequestBuilder().build();
        this.consents.get(request);

        this.assertRequestInfoNotHas("psuTppCustomerId");
    }

    @Test
    public void testVerifyGetConsentPsuTppCustomerWillBePassed() throws Exception {
        this.mockGetConsentResponse();

        GetConsentRequest request = new GetConsentRequestBuilder()
                .withPsuTppCustomerId("psuTppCustomerId")
                .build();
        this.consents.get(request);

        this.assertRequestInfoHas("psuTppCustomerId");
    }

    @Test
    public void testVerifyGetConsent() throws Exception {

        this.mockGetConsentResponse();

        GetConsentRequest request = new GetConsentRequestBuilder()
                .withAspspId("420e5cff-0e2a-4156-991a-f6eeef0478cf")
                .withMerchant("MerchantId", "MerchantName")
                .withIsLivePsuRequest(true)
                .withPsuAgent("PostmanRuntime/7.20.1")
                .withPsuIPAddress("127.0.0.1")
                .withPsuTppCustomerId("420e5cff-0e2a-4156-991a-f6eeef0478cf")
                .addConsentPermission(ConsentPermission.allPSD2)
                .addAccount("ACCNUMBR1234567", "EUR", "IBAN")
                .build();

        Consent consent = this.consents.get(request);

        Assert.assertNotNull(consent.consentRequestId);
        Assert.assertNotNull(consent.scaRedirectUri);
        Assert.assertNotNull(consent.xRequestId);
    }

    @Test
    public void testVerifyGetConsentMerchantWillBePassed() throws Exception {
        this.mockGetConsentResponse();

        GetConsentRequest request = new GetConsentRequestBuilder()
                .withMerchant("MerchantId", "MerchantName")
                .build();
        this.consents.get(request);

        this.assertRequestInfoHas("merchant");
    }

    @Test
    public void testVerifyGetConsentMerchantIsOptional() throws Exception {
        this.mockGetConsentResponse();

        GetConsentRequest request = new GetConsentRequestBuilder().build();
        this.consents.get(request);

        this.assertRequestInfoNotHas("merchant");
    }

    @Test
    public void testVerifyGetConsentPsuIpAddressWillBePassed() throws Exception {
        this.mockGetConsentResponse();

        GetConsentRequest request = new GetConsentRequestBuilder()
                .withPsuIPAddress("127.0.0.1")
                .build();
        this.consents.get(request);

        this.assertRequestInfoHas("psuIPAddress");
    }

    @Test
    public void testVerifyGetConsentPsuIpAddressIsOptional() throws Exception {
        this.mockGetConsentResponse();

        GetConsentRequest request = new GetConsentRequestBuilder().build();
        this.consents.get(request);

        this.assertRequestInfoNotHas("psuIPAddress");
    }

    @Test
    public void testVerifyGetConsentMultipleAccountsCanBeAdded() throws Exception {
        this.mockGetConsentResponse();

        GetConsentRequest request = new GetConsentRequestBuilder()
                .addAccount("ACCNUMBR1234567", "EUR", "IBAN")
                .addAccount("ACCNUMBR1234567", "EUR", "IBAN")
                .build();
        this.consents.get(request);

        Mockito.verify(this.mastercardAisClient).postJson(any(), argThat(jsonBody -> {
            return new JSONObject(jsonBody).getJSONArray("accounts").length() == 2;
        }));
    }

    @Test
    public void authConsentShouldAddPsuIpAddressWhenExists() throws Exception {
        HttpResponse authConsentResponse = this.mockAuthConsentResponse();
        Mockito.when(this.mastercardAisClient.postJson(any(), any())).thenReturn(authConsentResponse);

        AuthConsentRequest request = new AuthConsentRequestBuilder()
                .withPsuIPAddress("127.0.0.1")
                .withAuthorization("authorization")
                .build();
        this.consents.authorize(request);

        this.assertRequestInfoHas("psuIPAddress");
    }

    @Test
    public void authConsentShouldNotAddPsuIpAddressWhenNotExists() throws Exception {
        HttpResponse authConsentResponse = this.mockAuthConsentResponse();
        Mockito.when(this.mastercardAisClient.postJson(any(), any())).thenReturn(authConsentResponse);

        AuthConsentRequest request = new AuthConsentRequestBuilder()
                .withAuthorization("authorization")
                .build();
        this.consents.authorize(request);

        this.assertRequestInfoNotHas("psuIPAddress");
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
