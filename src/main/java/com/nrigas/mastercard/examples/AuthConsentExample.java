package com.nrigas.mastercard.examples;

import com.nrigas.mastercard.MastercardAis;
import com.nrigas.mastercard.MastercardAisConfig;
import com.nrigas.mastercard.model.AuthorizedConsent;
import com.nrigas.mastercard.request.AuthConsentRequest;
import com.nrigas.mastercard.requestBuilders.AuthConsentRequestBuilder;

public class AuthConsentExample {

	public static void main(String[] args) throws Exception {
		MastercardAisConfig config = new MastercardAisConfig(
				"./var/key.p12",
				"keyalias",
				"keystorepassword",
				"nowzQEeE32g6FnESTjKEM6bq2mECwDX2SdOqfL9zae61ec44!5dda6ce1d93d4e2392232d711c4afb3f0000000000000000",
				true
		);
		MastercardAis mastercardAis = new MastercardAis(config);

		AuthConsentRequest request = new AuthConsentRequestBuilder()
				.withAspspId("420e5cff-0e2a-4156-991a-f6eeef0478cf")
				.withMerchant("MerchantId", "MerchantName")
				.withIsLivePsuRequest(true)
				.withPsuAgent("PostmanRuntime/7.20.1")
				.withPsuIPAddress("127.0.0.1")
				.withAuthorization("code=UKaccountEsbGdTB2a9MbSdt53serRsv0aUK001&state=38948933-38ae-45af-953e-25a69fefa39e")
				.build();
		AuthorizedConsent consent = mastercardAis.consents().authorize(request);
	}
}
