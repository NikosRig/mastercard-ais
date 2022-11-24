package com.nrigas.mastercard.examples;

import com.nrigas.mastercard.MastercardAis;
import com.nrigas.mastercard.MastercardAisConfig;
import com.nrigas.mastercard.requestBuilders.DeleteConsentRequestBuilder;

public class DeleteConsentExample {

	public static void main(String[] args) throws Exception {

		MastercardAisConfig config = new MastercardAisConfig(
				"./var/key.p12",
				"keyalias",
				"keystorepassword",
				"nowzQEeE32g6FnESTjKEM6bq2mECwDX2SdOqfL9zae61ec44!5dda6ce1d93d4e2392232d711c4afb3f0000000000000000",
				true
		);
		MastercardAis mastercardAis = new MastercardAis(config);

		DeleteConsentRequestBuilder deleteConsentRequestBuilder = new DeleteConsentRequestBuilder();
		deleteConsentRequestBuilder.addAspspId("420e5cff-0e2a-4156-991a-f6eeef0478cf");
		deleteConsentRequestBuilder.addMerchant("MerchantId", "MerchantName");
		deleteConsentRequestBuilder.addConsentId("GFiTpF3:EBy5xGqQMatk");
		deleteConsentRequestBuilder.addPsuTppCustomerId("psuTppCustomerId");

		mastercardAis.consents().delete(deleteConsentRequestBuilder.build());
	}
}
