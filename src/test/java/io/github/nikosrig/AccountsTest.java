package io.github.nikosrig;

import io.github.nikosrig.model.Account;
import io.github.nikosrig.model.AccountList;
import io.github.nikosrig.request.GetAccountRequest;
import io.github.nikosrig.request.GetBalanceRequest;
import io.github.nikosrig.request.ListAccountsRequest;
import io.github.nikosrig.requestBuilders.GetAccountRequestBuilder;
import io.github.nikosrig.requestBuilders.GetBalanceRequestBuilder;
import io.github.nikosrig.requestBuilders.ListAccountsRequestBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.net.http.HttpResponse;

import static org.mockito.ArgumentMatchers.any;

public class AccountsTest extends TestCase {

	private Accounts accounts;

	@Before
	public void setUp() {
		super.setUp();
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

	@Test
	public void testListShouldParseResponse() throws Exception {
		this.mockListAccountsResponse();

		ListAccountsRequest request = new ListAccountsRequestBuilder().build();
		AccountList accountList = this.accounts.list(request);

		Assert.assertNotNull(accountList.accounts);
	}

	@Test
	public void testListShouldAddRequestParams() throws Exception {
		this.mockListAccountsResponse();

		ListAccountsRequest request = new ListAccountsRequestBuilder()
				.withConsentId("GFiTpF3:EBy5xGqQMatk")
				.withAspspId("420e5cff-0e2a-4156-991a-f6eeef0478cf")
				.withIsLivePsuRequest(true)
				.withPsuTppCustomerId("420e5cff-0e2a-4156-991a-f6eeef0478cf")
				.withPsuIPAddress("127.0.0.1")
				.withPsuAgent("PostmanRuntime/7.20.1")
				.withMerchant("MerchantId", "MerchantName")
				.build();
		this.accounts.list(request);

		this.assertRequestInfoHas("xRequestId");
		this.assertRequestInfoHas("consentId");
		this.assertRequestInfoHas("isLivePsuRequest");
		this.assertRequestInfoHas("aspspId");
		this.assertRequestInfoHas("psuTppCustomerId");
		this.assertRequestInfoHas("psuIPAddress");
		this.assertRequestInfoHas("psuAgent");
		this.assertRequestInfoHas("merchant");
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

	private void mockListAccountsResponse() throws Exception {
		String responseBody = "{\"originalRequestInfo\":{\"xRequestId\":\"444e4567-e55b-12d3-a456-426655448888\"},\"accounts\":[{\"resourceId\":\"account1\",\"currency\":\"USD\",\"accountHolderType\":\"Personal\",\"holderName\":\"John Doe\",\"accountType\":\"ASTC01\",\"nameClient\":\"Accounts name client\",\"name\":\"My savings account\",\"accountNumber\":\"70000170000005\",\"schemeName\":\"BBAN\",\"accountPsuRelations\":[{\"typeOfRelation\":\"Owner\"}]}]}";
		HttpResponse response = this.mockHttpResponse(responseBody, 200);
		Mockito.when(this.mastercardAisClient.postJson(any(), any())).thenReturn(response);
	}
}
