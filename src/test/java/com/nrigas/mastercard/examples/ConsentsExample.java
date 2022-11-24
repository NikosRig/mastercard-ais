package com.nrigas.mastercard.examples;

import com.nrigas.mastercard.MastercardAis;
import com.nrigas.mastercard.MastercardAisConfig;
import com.nrigas.mastercard.TestCase;
import com.nrigas.mastercard.model.Consent;
import com.nrigas.mastercard.model.ConsentPermission;
import com.nrigas.mastercard.requestBuilders.GetConsentRequestBuilder;

public class ConsentsExample extends TestCase {

	public static void main(String[] args) throws Exception {

		MastercardAisConfig config = new MastercardAisConfig(
				"./var/key.p12",
				"keyalias",
				"keystorepassword",
				"nowzQEeE32g6FnESTjKEM6bq2mECwDX2SdOqfL9zae61ec44!5dda6ce1d93d4e2392232d711c4afb3f0000000000000000",
				true
		);
		MastercardAis mastercardAis = new MastercardAis(config);

		GetConsentRequestBuilder consentRequestBuilder = new GetConsentRequestBuilder();
		consentRequestBuilder.addAspspId("420e5cff-0e2a-4156-991a-f6eeef0478cf");
		consentRequestBuilder.addTppRedirectURI("https://tpp-ob.com/callback");
		consentRequestBuilder.addMerchant("MerchantId", "MerchantName");
		consentRequestBuilder.addPsu(true, "PostmanRuntime/7.20.1", "127.0.0.1", "psuTppCustomerId");
		consentRequestBuilder.addCredentials("DE357543513");
		consentRequestBuilder.addConsentPermission(ConsentPermission.allPSD2);
		consentRequestBuilder.addAccount("ACCNUMBR1234567", "EUR", "IBAN");

		Consent consent = mastercardAis.consents()
				.get(consentRequestBuilder.build());
	}
}
