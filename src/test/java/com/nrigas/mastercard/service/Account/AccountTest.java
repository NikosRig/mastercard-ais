package com.nrigas.mastercard.service.Account;

import com.nrigas.mastercard.TestCase;
import com.nrigas.mastercard.http.MastercardAisClient;
import com.nrigas.mastercard.request.GetAccountRequest;
import com.nrigas.mastercard.requestBuilders.GetAccountRequestBuilder;
import com.nrigas.mastercard.service.Account.response.GetAccountResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.net.http.HttpResponse;

import static org.mockito.ArgumentMatchers.any;

public class AccountTest extends TestCase {

	private MastercardAisClient mastercardAisClient;
	private Account account;

	@Before
	public void setUp() {
		this.mastercardAisClient = Mockito.mock(MastercardAisClient.class);
		this.account = new Account(this.mastercardAisClient);
	}

	@Test
	public void getAccountShouldParseResponse() throws Exception {
		HttpResponse getAccountMockResponse = this.mockGetAccountResponse();
		Mockito.when(this.mastercardAisClient.postJson(any(), any())).thenReturn(getAccountMockResponse);

		GetAccountRequest getAccountRequest = new GetAccountRequestBuilder()
				.withAspspId("420e5cff-0e2a-4156-991a-f6eeef0478cf")
				.withMerchant("MerchantId", "MerchantName")
				.withConsentId("GFiTpF3:EBy5xGqQMatk")
				.withAccountId("aa:q648383844dhhfHhTV")
				.withPsu(true, "PostmanRuntime/7.20.1", "127.0.0.1", null)
				.build();
		GetAccountResponse getAccountResponse = this.account.get(getAccountRequest);

		Assert.assertNotNull(getAccountResponse.account.resourceId);
		Assert.assertNotNull(getAccountResponse.account.currency);
		Assert.assertNotNull(getAccountResponse.account.accountHolderType);
		Assert.assertNotNull(getAccountResponse.account.accountType);
		Assert.assertNotNull(getAccountResponse.account.nameClient);
		Assert.assertNotNull(getAccountResponse.account.name);
		Assert.assertNotNull(getAccountResponse.account.holderName);
		Assert.assertNotNull(getAccountResponse.account.holderNameAddress);
		Assert.assertNotNull(getAccountResponse.account.accountNumber);
		Assert.assertNotNull(getAccountResponse.account.schemeName);
		Assert.assertNotNull(getAccountResponse.account.auxData);
		Assert.assertNotNull(getAccountResponse.account.holderNameAddress);
		Assert.assertNotNull(getAccountResponse.account.accountPsuRelations);
		Assert.assertNotNull(getAccountResponse.account.holderAddress);
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
