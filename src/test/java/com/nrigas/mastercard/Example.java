package com.nrigas.mastercard;

import com.nrigas.mastercard.model.ConsentPermission;
import com.nrigas.mastercard.request.GetConsentRequest;
import com.nrigas.mastercard.requestBuilders.GetConsentRequestBuilder;
import com.nrigas.mastercard.response.GetConsentResponse;

public class Example {

	public static void main(String[] args) throws Exception {

		MastercardAisConfig config = new MastercardAisConfig(
				"./var/key.p12",
				"keyalias",
				"keystorepassword",
				"nowzQEeE32g6FnESTjKEM6bq2mECwDX2SdOqfL9zae61ec44!5dda6ce1d93d4e2392232d711c4afb3f0000000000000000",
				true
		);
		MastercardAis mastercardAis = new MastercardAis(config);

		GetConsentRequest getConsentRequest = new GetConsentRequestBuilder()
				.withAspsId("420e5cff-0e2a-4156-991a-f6eeef0478cf")
				.withTppRedirectUri("https://tpp-ob.com/callback")
				.withMerchant("MerchantId", "MerchantName")
				.withPsu(true, "PostmanRuntime/7.20.1", "127.0.0.1", "psuTppCustomerId")
				.addConsentPermission(ConsentPermission.allPSD2)
				.withCredentials("DE357543513")
				.build();
		GetConsentResponse getConsentResponse = mastercardAis.consent().get(getConsentRequest);
	}
}
