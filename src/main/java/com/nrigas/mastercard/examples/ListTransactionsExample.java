package com.nrigas.mastercard.examples;

import com.nrigas.mastercard.MastercardAis;
import com.nrigas.mastercard.MastercardAisConfig;
import com.nrigas.mastercard.model.TransactionList;
import com.nrigas.mastercard.request.ListTransactionsRequest;
import com.nrigas.mastercard.requestBuilders.ListTransactionsRequestBuilder;

public class ListTransactionsExample {

	public static void main(String[] args) throws Exception {

			MastercardAisConfig config = new MastercardAisConfig(
					"./var/key.p12",
					"keyalias",
					"keystorepassword",
					"nowzQEeE32g6FnESTjKEM6bq2mECwDX2SdOqfL9zae61ec44!5dda6ce1d93d4e2392232d711c4afb3f0000000000000000",
					true
			);
			MastercardAis mastercardAis = new MastercardAis(config);

			ListTransactionsRequest request = new ListTransactionsRequestBuilder()
				.withAccountId("aa:q648383844dhhfHhTV")
				.withConsentId("GFiTpF3:EBy5xGqQMatk")
				.withAspspId("420e5cff-0e2a-4156-991a-f6eeef0478cf")
				.withIsLivePsuRequest(true)
				.withPsuTppCustomerId("420e5cff-0e2a-4156-991a-f6eeef0478cf")
				.withPsuIPAddress("127.0.0.1")
				.withPsuAgent("PostmanRuntime/7.20.1")
				.withMerchant("MerchantId", "MerchantName")
				.withLimit(20)
				.withBookingDateFrom("2018-09-23")
				.withBookingTo("2018-11-21")
				.build();

			TransactionList transactionList = mastercardAis.transactions().list(request);
		}
}