package io.github.nikosrig.examples;

import io.github.nikosrig.MastercardAis;
import io.github.nikosrig.model.RawConsent;
import io.github.nikosrig.request.GetRawConsentRequest;
import io.github.nikosrig.requestBuilders.GetRawConsentRequestBuilder;

public class GetRawConsentExample {

	public static void main(String[] args) throws Exception {

		MastercardAis mastercardAis = new MastercardAis.Builder()
				.enableSandboxMode()
				.withPkcs12FilePath("./var/key.p12")
				.withSigningKeyAlias("keyalias")
				.withSigningKeyPassword("keystorepassword")
				.withConsumerKey("nowzQEeE32g6FnESTjKEM6bq2mECwDX2SdOqfL9zae61ec44!5dda6ce1d93d4e2392232d711c4afb3f0000000000000000")
				.build();

		GetRawConsentRequest request = new GetRawConsentRequestBuilder()
				.withAspspId("420e5cff-0e2a-4156-991a-f6eeef0478cf")
				.withConsentId("GFiTpF3:EBy5xGqQMatk")
				.withMerchant("MerchantId", "MerchantName")
				.withIsLivePsuRequest(true)
				.withPsuAgent("PostmanRuntime/7.20.1")
				.withPsuIPAddress("127.0.0.1")
				.build();
		RawConsent rawConsent = mastercardAis.consents().getRaw(request);
	}
}
