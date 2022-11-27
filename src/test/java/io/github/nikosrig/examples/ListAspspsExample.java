package io.github.nikosrig.examples;

import io.github.nikosrig.MastercardAis;
import io.github.nikosrig.model.AspspList;
import io.github.nikosrig.request.ListAspspsRequest;
import io.github.nikosrig.request.model.AdditionalData;
import io.github.nikosrig.requestBuilders.ListAspspsRequestBuilder;

public class ListAspspsExample {

	public static void main(String[] args) throws Exception {

		MastercardAis mastercardAis = new MastercardAis.Builder()
				.enableSandboxMode()
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