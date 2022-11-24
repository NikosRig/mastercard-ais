package com.nrigas.mastercard.service;

import com.nrigas.mastercard.Accounts;
import com.nrigas.mastercard.TestCase;
import com.nrigas.mastercard.http.MastercardAisClient;
import com.nrigas.mastercard.request.GetAccountRequest;
import com.nrigas.mastercard.requestBuilders.GetAccountRequestBuilder;
import com.nrigas.mastercard.response.GetAccountResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.net.http.HttpResponse;

import static org.mockito.ArgumentMatchers.any;

public class AccountsTest extends TestCase {

	private MastercardAisClient mastercardAisClient;
	private Accounts accounts;

	@Before
	public void setUp() {
		this.mastercardAisClient = Mockito.mock(MastercardAisClient.class);
		this.accounts = new Accounts(this.mastercardAisClient);
	}

	@Test
	public void getAccountShouldParseResponse() throws Exception {
		HttpResponse getAccountMockResponse = this.mockGetAccountResponse();
		Mockito.when(this.mastercardAisClient.postJson(any(), any())).thenReturn(getAccountMockResponse);

		GetAccountRequest request = new GetAccountRequestBuilder()
				.withAspspId("420e5cff-0e2a-4156-991a-f6eeef0478cf")
				.withMerchant("MerchantId", "MerchantName")
				.withConsentId("GFiTpF3:EBy5xGqQMatk")
				.withAccountId("aa:q648383844dhhfHhTV")
				.withIsLivePsuRequest(true)
				.withPsuAgent("PostmanRuntime/7.20.1")
				.withPsuIPAddress("127.0.0.1")
				.withPsuTppCustomerId("420e5cff-0e2a-4156-991a-f6eeef0478cf")
				.build();
		GetAccountResponse response = this.accounts.get(request);

		Assert.assertNotNull(response.account.resourceId);
		Assert.assertNotNull(response.account.currency);
		Assert.assertNotNull(response.account.accountHolderType);
		Assert.assertNotNull(response.account.accountType);
		Assert.assertNotNull(response.account.nameClient);
		Assert.assertNotNull(response.account.name);
		Assert.assertNotNull(response.account.holderName);
		Assert.assertNotNull(response.account.holderNameAddress);
		Assert.assertNotNull(response.account.schemeName);
		Assert.assertNotNull(response.account.auxData);
		Assert.assertNotNull(response.account.holderNameAddress);
		Assert.assertNotNull(response.account.accountPsuRelations);
		Assert.assertNotNull(response.account.holderAddress);
	}

	private HttpResponse mockGetAccountResponse() {
		String responseBody = "{\"originalRequestInfo\":{\"xRequestId\":\"444e4567-e55b-12d3-a456-426655448888\"},\"account\":{\"resourceId\":\"account1\",\"currency\":\"USD\",\"accountHolderType\":\"Personal\",\"accountType\":\"ASTC01\",\"nameClient\":\"Accounts name client\",\"name\":\"My savings account\",\"holderName\":\"John Doe\",\"holderAddress\":{\"street\":\"Street\",\"buildingNumber\":\"15\",\"city\":\"City\",\"postalCode\":\"PostCode\",\"countrySubDivision\":\"Division\",\"country\":\"CC\"},\"holderNameAddress\":[\"Street Street\"],\"accountPsuRelations\":[{\"typeOfRelation\":\"Owner\"}],\"accountNumber\":\"70000170000005\",\"schemeName\":\"BBAN\",\"auxData\":\"{\\\"additionalProp1\\\": \\\"somePropertyValue1\\\"}\"}}";
		return this.mockHttpResponse(responseBody, 200);
	}

	private HttpResponse mockListAccountsResponse() {
		String responseBody = "{\"originalRequestInfo\":{\"xRequestId\":\"444e4567-e55b-12d3-a456-426655448888\"},\"account\":{\"resourceId\":\"account1\",\"currency\":\"USD\",\"accountHolderType\":\"Personal\",\"accountType\":\"ASTC01\",\"nameClient\":\"Accounts name client\",\"name\":\"My savings account\",\"holderName\":\"John Doe\",\"holderAddress\":{\"street\":\"Street\",\"buildingNumber\":\"15\",\"city\":\"City\",\"postalCode\":\"PostCode\",\"countrySubDivision\":\"Division\",\"country\":\"CC\"},\"holderNameAddress\":[\"Street Street\"],\"accountPsuRelations\":[{\"typeOfRelation\":\"Owner\"}],\"accountNumber\":\"70000170000005\",\"schemeName\":\"BBAN\",\"auxData\":\"{\\\"additionalProp1\\\": \\\"somePropertyValue1\\\"}\"}}";
		return this.mockHttpResponse(responseBody, 200);
	}
}
