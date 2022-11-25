package com.nrigas.mastercard;

import com.nrigas.mastercard.model.AuthorizedConsent;
import com.nrigas.mastercard.model.Consent;
import com.nrigas.mastercard.model.RawConsent;
import com.nrigas.mastercard.request.AuthConsentRequest;
import com.nrigas.mastercard.request.DeleteConsentRequest;
import com.nrigas.mastercard.request.GetConsentRequest;
import com.nrigas.mastercard.request.GetRawConsentRequest;
import com.nrigas.mastercard.request.requestInfo.ConsentPermission;
import com.nrigas.mastercard.requestBuilders.AuthConsentRequestBuilder;
import com.nrigas.mastercard.requestBuilders.DeleteConsentRequestBuilder;
import com.nrigas.mastercard.requestBuilders.GetConsentRequestBuilder;
import com.nrigas.mastercard.requestBuilders.GetRawConsentRequestBuilder;
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
    public void testVerifyGetConsentMerchantWillHasCredentials() throws Exception {
        this.mockGetConsentResponse();

        GetConsentRequest request = new GetConsentRequestBuilder()
                .withMerchant("MerchantId", "MerchantName")
                .withCredentials("iban")
                .build();
        this.consents.get(request);

        Mockito.verify(this.mastercardAisClient).postJson(any(), argThat(jsonBody -> {
            JSONObject credentials = new JSONObject(jsonBody).getJSONObject("requestInfo")
                    .getJSONObject("credentials");
           return credentials.has("iban");
        }));
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
    public void testAuthConsentShouldAddPsuIpAddressWhenExists() throws Exception {
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
    public void testAuthConsentShouldNotAddPsuIpAddressWhenNotExists() throws Exception {
        HttpResponse authConsentResponse = this.mockAuthConsentResponse();
        Mockito.when(this.mastercardAisClient.postJson(any(), any())).thenReturn(authConsentResponse);

        AuthConsentRequest request = new AuthConsentRequestBuilder()
                .withAuthorization("authorization")
                .build();
        this.consents.authorize(request);

        this.assertRequestInfoNotHas("psuIPAddress");
    }

    @Test
    public void testAuthConsentShouldParseResponse() throws Exception {
        HttpResponse authConsentResponse = this.mockAuthConsentResponse();
        Mockito.when(this.mastercardAisClient.postJson(any(), any())).thenReturn(authConsentResponse);

        AuthConsentRequest request = new AuthConsentRequestBuilder()
                .withAuthorization("authorization")
                .build();
        AuthorizedConsent authorizedConsent = this.consents.authorize(request);

        Assert.assertNotNull(authorizedConsent.consentRequestId);
        Assert.assertNotNull(authorizedConsent.consentId);
        Assert.assertNotNull(authorizedConsent.signatureStatus);
    }

    @Test
    public void testGetRawConsentRequestParams() throws Exception {
        this.mockRawConsentResponse();

        GetRawConsentRequest request = new GetRawConsentRequestBuilder()
                .withConsentId("GFiTpF3:EBy5xGqQMatk")
                .withAspspId("420e5cff-0e2a-4156-991a-f6eeef0478c")
                .withIsLivePsuRequest(true)
                .withPsuIPAddress("192.168.0.1")
                .withPsuAgent("PostmanRuntime/7.20.1")
                .withMerchant("MerchantId", "MerchantName")
                .build();
        this.consents.getRaw(request);

        this.assertRequestInfoHas("xRequestId");
        this.assertRequestInfoHas("aspspId");
        this.assertRequestInfoHas("consentId");
        this.assertRequestInfoHas("isLivePsuRequest");
        this.assertRequestInfoHas("psuIPAddress");
        this.assertRequestInfoHas("psuAgent");
        this.assertRequestInfoHas("merchant");
    }

    @Test
    public void testGetRawConsentShouldParseResponse() throws Exception {
        this.mockRawConsentResponse();

        GetRawConsentRequest request = new GetRawConsentRequestBuilder()
                .withConsentId("GFiTpF3:EBy5xGqQMatk")
                .withAspspId("420e5cff-0e2a-4156-991a-f6eeef0478c")
                .build();
        RawConsent rawConsent = this.consents.getRaw(request);

        Assert.assertNotNull(rawConsent.rawConsent);
        Assert.assertNotNull(rawConsent.originalRequestInfo);
    }

    @Test
    public void testDeleteConsentRequestParams() throws Exception {
        this.mockRawConsentResponse();

        DeleteConsentRequest request = new DeleteConsentRequestBuilder()
                .withConsentId("GFiTpF3:EBy5xGqQMatk")
                .withAspspId("420e5cff-0e2a-4156-991a-f6eeef0478c")
                .withPsuTppCustomerId("420e5cff-0e2a-4156-991a-f6eeef0478cf")
                .withMerchant("MerchantId", "MerchantName")
                .build();
        this.consents.delete(request);

        this.assertRequestInfoHas("xRequestId");
        this.assertRequestInfoHas("aspspId");
        this.assertRequestInfoHas("psuTppCustomerId");
        this.assertRequestInfoHas("merchant");
        this.assertRequestHas("consentId");
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

    private void mockRawConsentResponse() throws Exception {
        String responseBody = "{\"originalRequestInfo\":{\"xRequestId\":\"444e4567-e55b-12d3-a456-426655448888\"},\"rawConsent\":\"ewogIHNjb3BlOiBhY2NvdW50cywKICB0b2tlbl90eXBlOiBCZWFyZXIsCiAgYWNjZXNzX3Rva2VuOiBXMzRpVFhlVFZVZGtpRFpvVDlXVFVUMFE1eW5Hdk1uc3NXbUZ5bmMwVGlzY0ZlbEpiTWM0WmhTaXBHalRtNUd3dzVGSWs2TVVqeE9VeVV3TVp3UU1pSXlMMDNBZmpiU2RHekdWNGlpVHlpNmkwa3pkT05HMk1DaXd4WVVTYjNRaWt5OTRNT1dJc05SVG1GT0pYYnVjNU5paWkyMDAyWTJwVnk0VzVPajVKY1pZVzJSWVVNMjJZaU4ya1pZSlpVdGx0amJjM1hKV0FJbHZPV2lsSmJJbFljYko5TXRXT0lGMkFCWlZDalZjMFVJbiwKICByZWZyZXNoX3Rva2VuOiBDTzM0V01tV0c1TWl2V1NVWjAzT2M1cGVGYzVKbk1VVHdsTWlDYVlaYkc1azlGeUFRNElCOVdVWUxrYk9HU2N5VjB0VGMyMzNzazJ3MmZzNHlaWVpKMm5zd21pdHhpemNWaU1Oak5ZNkpqQXZSVVVKeTZieU9zSjBsSXVoVEcyV2RNaURsZGlWWjJrMFhaU2p3STR5TlZsVVdpalJqRlFPOXQ1TUl6eWNpNG41YnBZMlRKajAyRmJNTVhVWDNJYmlObVRZWW53VFdPR21PVFRJYmlHZWlvUXhqWldWSWMwMmlXQVVraUpDT2xjaSwKICBleHBpcmVzX2luOiAzNjAwCn0K\"}";
        HttpResponse response = this.mockHttpResponse(responseBody, 200);
        Mockito.when(this.mastercardAisClient.postJson(any(), any())).thenReturn(response);
    }

    private HttpResponse mockAuthConsentResponse() {
        String responseBody = "{\"originalRequestInfo\":{\"xRequestId\":\"444e4567-e55b-12d3-a456-426655448888\"},\"consentRequestId\":\"rq:7JKvT4jY3oc4xxAdSqdYawemSQP:5zKtXEAq\",\"consentId\":\"GFiTpF3:EBy5xGqQMatk\",\"signatureStatus\":\"VALID\"}";
        return this.mockHttpResponse(responseBody, 200);
    }

}
