package com.nrigas.mastercard.examples;

import com.nrigas.mastercard.MastercardAis;
import com.nrigas.mastercard.model.AccountList;
import com.nrigas.mastercard.request.ListAccountsRequest;
import com.nrigas.mastercard.requestBuilders.ListAccountsRequestBuilder;

public class ListAccountsExample {

	public static void main(String[] args) throws Exception {

		MastercardAis mastercardAis = new MastercardAis.Builder()
				.enableSandboxMode(true)
				.withPkcs12FilePath("./var/key.p12")
				.withSigningKeyAlias("keyalias")
				.withSigningKeyPassword("keystorepassword")
				.withConsumerKey("nowzQEeE32g6FnESTjKEM6bq2mECwDX2SdOqfL9zae61ec44!5dda6ce1d93d4e2392232d711c4afb3f0000000000000000")
				.build();

		ListAccountsRequest request = new ListAccountsRequestBuilder()
				.withAspspId("420e5cff-0e2a-4156-991a-f6eeef0478cf")
				.withMerchant("MerchantId", "MerchantName")
				.withConsentId("GFiTpF3:EBy5xGqQMatk")
				.withIsLivePsuRequest(true)
				.withPsuAgent("PostmanRuntime/7.20.1")
				.withPsuIPAddress("127.0.0.1")
				.withPsuTppCustomerId("420e5cff-0e2a-4156-991a-f6eeef0478cf")
				.build();
		AccountList accountList = mastercardAis.accounts().list(request);
	}
}