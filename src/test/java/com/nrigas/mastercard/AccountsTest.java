package com.nrigas.mastercard;

import com.nrigas.mastercard.http.MastercardAisClient;
import com.nrigas.mastercard.model.Account;
import com.nrigas.mastercard.request.GetAccountRequest;
import com.nrigas.mastercard.request.GetBalanceRequest;
import com.nrigas.mastercard.requestBuilders.GetAccountRequestBuilder;
import com.nrigas.mastercard.requestBuilders.GetBalanceRequestBuilder;
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
	public void testGetAccountShouldParseResponse() throws Exception {
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
		Account account = this.accounts.get(request);

		Assert.assertNotNull(account.resourceId);
		Assert.assertNotNull(account.currency);
		Assert.assertNotNull(account.accountHolderType);
		Assert.assertNotNull(account.accountType);
		Assert.assertNotNull(account.nameClient);
		Assert.assertNotNull(account.name);
		Assert.assertNotNull(account.holderName);
		Assert.assertNotNull(account.holderNameAddress);
		Assert.assertNotNull(account.schemeName);
		Assert.assertNotNull(account.auxData);
		Assert.assertNotNull(account.holderNameAddress);
		Assert.assertNotNull(account.accountPsuRelations);
		Assert.assertNotNull(account.holderAddress);
	}

	@Test
	public void testBalanceShouldParseResponse() throws Exception {
		this.mockBalanceResponse();

		GetBalanceRequest request = new GetBalanceRequestBuilder()
				.withAccountId("aa:q648383844dhhfHhTV")
				.build();
		Account account = this.accounts.balance(request);

		Assert.assertNotNull(account.balances);
		Assert.assertNotNull(account.name);
		Assert.assertNotNull(account.resourceId);
	}

	private void mockBalanceResponse() throws Exception {
		String responseBody = "{\"originalRequestInfo\":{\"xRequestId\":\"444e4567-e55b-12d3-a456-426655448888\"},\"account\":{\"resourceId\":\"AccountReference1\",\"name\":\"My savings account\",\"balances\":[{\"balanceAmount\":{\"currency\":\"USD\",\"amount\":100.23},\"balanceType\":\"Current\",\"creditDebitIndicator\":\"CREDIT\",\"dateTime\":\"2021-05-21T08:30:00Z\"}]}}";
		HttpResponse response = this.mockHttpResponse(responseBody, 200);
		Mockito.when(this.mastercardAisClient.postJson(any(), any())).thenReturn(response);
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
