package com.nrigas.mastercard.examples;

import com.nrigas.mastercard.MastercardAis;
import com.nrigas.mastercard.model.AspspList;
import com.nrigas.mastercard.request.ListAspspsRequest;
import com.nrigas.mastercard.request.model.AdditionalData;
import com.nrigas.mastercard.requestBuilders.ListAspspsRequestBuilder;

public class ListAspspsExample {

	public static void main(String[] args) throws Exception {

		MastercardAis mastercardAis = new MastercardAis.Builder()
				.isSandboxMode(true)
				.withPkcs12FilePath("./var/key.p12")
				.withSigningKeyAlias("keyalias")
				.withSigningKeyPassword("keystorepassword")
				.withConsumerKey("nowzQEeE32g6FnESTjKEM6bq2mECwDX2SdOqfL9zae61ec44!5dda6ce1d93d4e2392232d711c4afb3f0000000000000000")
				.build();

		ListAspspsRequest request = new ListAspspsRequestBuilder()
				.withId("018d02c8-9be6-4363-9f3a-9009b2c89768")
				.withName("Apollo Bank")
				.withCountry("GB")
				.withLimit(20)
				.addAdditionalData(AdditionalData.capabilities)
				.addAdditionalData(AdditionalData.credentials)
				.addAdditionalData(AdditionalData.health)
				.addAdditionalData(AdditionalData.logo)
				.build();
		AspspList aspspList = mastercardAis.aspsps().list(request);
	}
}