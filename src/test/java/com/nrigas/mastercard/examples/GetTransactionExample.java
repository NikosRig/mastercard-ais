package com.nrigas.mastercard.examples;

import com.nrigas.mastercard.MastercardAis;
import com.nrigas.mastercard.model.Transaction;
import com.nrigas.mastercard.request.GetTransactionRequest;
import com.nrigas.mastercard.requestBuilders.GetTransactionRequestBuilder;

public class GetTransactionExample {

	public static void main(String[] args) throws Exception {

		MastercardAis mastercardAis = new MastercardAis.Builder()
				.isSandboxMode(true)
				.withPkcs12FilePath("./var/key.p12")
				.withSigningKeyAlias("keyalias")
				.withSigningKeyPassword("keystorepassword")
				.withConsumerKey("nowzQEeE32g6FnESTjKEM6bq2mECwDX2SdOqfL9zae61ec44!5dda6ce1d93d4e2392232d711c4afb3f0000000000000000")
				.build();

		GetTransactionRequest request = new GetTransactionRequestBuilder()
				.withConsentId("MatkBJbqtZ8sPNznYtfV:5g")
				.withAspspId("b806ae68-a45b-49d6-b25a-69fdb81dede6")
				.withIsLivePsuRequest(false)
				.withMerchant("MerchantId", "MerchantName")
				.withPsuAgent("PostmanRuntime/7.20.1")
				.withPsuIPAddress("192.168.0.1")
				.withPsuTppCustomerId("420e5cff-0e2a-4156-991a-f6eeef0478cf")
				.withAccountId("qqCfw:XwAa:665hs5:r55dM")
				.withTransactionId("7ccs6s5:r55a:4MctP")
				.build();
		Transaction transaction = mastercardAis.transactions().get(request);
	}
}
