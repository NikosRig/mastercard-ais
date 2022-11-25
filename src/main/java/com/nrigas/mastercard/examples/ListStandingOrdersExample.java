package com.nrigas.mastercard.examples;

import com.nrigas.mastercard.MastercardAis;
import com.nrigas.mastercard.MastercardAisConfig;
import com.nrigas.mastercard.model.StandingOrdersList;
import com.nrigas.mastercard.request.ListStandingOrdersRequest;
import com.nrigas.mastercard.requestBuilders.ListStandingOrdersRequestBuilder;

public class ListStandingOrdersExample {

	public static void main(String[] args) throws Exception {

		MastercardAisConfig config = new MastercardAisConfig(
				"./var/key.p12",
				"keyalias",
				"keystorepassword",
				"nowzQEeE32g6FnESTjKEM6bq2mECwDX2SdOqfL9zae61ec44!5dda6ce1d93d4e2392232d711c4afb3f0000000000000000",
				true
		);
		MastercardAis mastercardAis = new MastercardAis(config);

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
		StandingOrdersList standingOrdersList = mastercardAis.standingOrders().list(request);
	}
}