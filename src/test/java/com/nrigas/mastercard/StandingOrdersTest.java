package com.nrigas.mastercard;

import com.nrigas.mastercard.model.StandingOrdersList;
import com.nrigas.mastercard.request.ListStandingOrdersRequest;
import com.nrigas.mastercard.requestBuilders.ListStandingOrdersRequestBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.net.http.HttpResponse;

import static org.mockito.ArgumentMatchers.any;

public class StandingOrdersTest extends TestCase {

	private StandingOrders standingOrders;

	@Before
	public void setUp() {
		super.setUp();
		this.standingOrders = new StandingOrders(this.mastercardAisClient);
	}

	@Test
	public void testListShouldAddRequestParams() throws Exception {
		this.mockListStandingOrdersResponse();

		ListStandingOrdersRequest request = new ListStandingOrdersRequestBuilder()
				.withAspspId("420e5cff-0e2a-4156-991a-f6eeef0478cf")
				.withMerchant("MerchantId", "MerchantName")
				.withConsentId("GFiTpF3:EBy5xGqQMatk")
				.withIsLivePsuRequest(true)
				.withPsuAgent("PostmanRuntime/7.20.1")
				.withPsuIPAddress("127.0.0.1")
				.withPsuTppCustomerId("420e5cff-0e2a-4156-991a-f6eeef0478cf")
				.withLimit(20)
				.withAccountId("aa:q648383844dhhfHhTV")
				.withOffset("ofset4prev$earch12345")
				.build();
		this.standingOrders.list(request);

		this.assertRequestInfoHas("xRequestId");
		this.assertRequestInfoHas("consentId");
		this.assertRequestInfoHas("aspspId");
		this.assertRequestInfoHas("psuTppCustomerId");
		this.assertRequestInfoHas("isLivePsuRequest");
		this.assertRequestInfoHas("psuIPAddress");
		this.assertRequestInfoHas("psuAgent");
		this.assertRequestInfoHas("merchant");
		this.assertRequestHas("accountId");
		this.assertRequestHas("limit");
		this.assertRequestHas("offset");
	}

	@Test
	public void testListShouldParseResponse() throws Exception {
		this.mockListStandingOrdersResponse();

		ListStandingOrdersRequest request = new ListStandingOrdersRequestBuilder()
				.withAspspId("420e5cff-0e2a-4156-991a-f6eeef0478cf")
				.withMerchant("MerchantId", "MerchantName")
				.withConsentId("GFiTpF3:EBy5xGqQMatk")
				.withIsLivePsuRequest(true)
				.withPsuAgent("PostmanRuntime/7.20.1")
				.withPsuIPAddress("127.0.0.1")
				.withPsuTppCustomerId("420e5cff-0e2a-4156-991a-f6eeef0478cf")
				.withLimit(20)
				.withAccountId("aa:q648383844dhhfHhTV")
				.withOffset("ofset4prev$earch12345")
				.build();
		StandingOrdersList standingOrdersList = this.standingOrders.list(request);

		Assert.assertNotNull(standingOrdersList.standingOrders);
		Assert.assertNotNull(standingOrdersList.offset);
	}

	private void mockListStandingOrdersResponse() throws Exception {
		String responseBody = "{\"originalRequestInfo\":{\"xRequestId\":\"444e4567-e55b-12d3-a456-426655448888\"},\"offset\":\"ofset4prev$earch12345\",\"standingOrders\":[{\"standingOrderId\":\"standingorderreference\",\"firstPayment\":{\"date\":\"2021-06-25\",\"currency\":\"USD\",\"amount\":100.23},\"nextPayment\":{\"date\":\"2021-06-25\",\"currency\":\"USD\",\"amount\":100.23},\"finalPayment\":{\"date\":\"2021-06-25\",\"currency\":\"USD\",\"amount\":100.23},\"schedule\":{\"frequency\":\"EvryDay\"},\"recipientAccount\":{\"schemeName\":\"UK.OBIE.IBAN\",\"identification\":\"70000170000005\",\"name\":\"John Doe\"},\"reference\":\"Reference1\",\"status\":\"ACTIVE\"}]}";
		HttpResponse response = this.mockHttpResponse(responseBody, 200);
		Mockito.when(this.mastercardAisClient.postJson(any(), any())).thenReturn(response);
	}
}
