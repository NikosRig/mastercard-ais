package com.nrigas.mastercard.examples;

import com.nrigas.mastercard.MastercardAis;
import com.nrigas.mastercard.MastercardAisConfig;
import com.nrigas.mastercard.model.AspspList;
import com.nrigas.mastercard.request.ListAspspsRequest;
import com.nrigas.mastercard.request.model.AdditionalData;
import com.nrigas.mastercard.requestBuilders.ListAspspsRequestBuilder;

public class ListAspspsExample {

	public static void main(String[] args) throws Exception {

		MastercardAisConfig config = new MastercardAisConfig(
				"./var/key.p12",
				"keyalias",
				"keystorepassword",
				"nowzQEeE32g6FnESTjKEM6bq2mECwDX2SdOqfL9zae61ec44!5dda6ce1d93d4e2392232d711c4afb3f0000000000000000",
				true
		);
		MastercardAis mastercardAis = new MastercardAis(config);
		ListAspspsRequest request = new ListAspspsRequestBuilder()
				.withId("123e4567-e89b-12d3-a456-426655440000")
				.withName("Wood bank")
				.withLimit(20)
				.addAdditionalData(AdditionalData.capabilities)
				.addAdditionalData(AdditionalData.credentials)
				.addAdditionalData(AdditionalData.health)
				.addAdditionalData(AdditionalData.logo)
				.build();
		AspspList aspspList = mastercardAis.aspsps().list(request);
	}
}