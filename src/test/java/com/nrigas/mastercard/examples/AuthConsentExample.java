package com.nrigas.mastercard.examples;

import com.nrigas.mastercard.MastercardAis;
import com.nrigas.mastercard.MastercardAisConfig;
import com.nrigas.mastercard.request.AuthConsentRequest;
import com.nrigas.mastercard.requestBuilders.AuthConsentRequestBuilder;
import com.nrigas.mastercard.service.Consent.response.AuthorizeConsentResponse;

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

		AuthConsentRequest authConsentRequest = new AuthConsentRequestBuilder()
				.withAspspId("420e5cff-0e2a-4156-991a-f6eeef0478cf")
				.withMerchant("MerchantId", "MerchantName")
				.withAuthorization("code=UKaccountEsbGdTB2a9MbSdt53serRsv0aUK001&state=c4ebdeb0-fdce-4879-ab84-62fdfaa28b80")
				.withPsu(true, "PostmanRuntime/7.20.1", "127.0.0.1")
				.build();
		AuthorizeConsentResponse authConsentResponse = mastercardAis.consents().authorize(authConsentRequest);
	}
}
