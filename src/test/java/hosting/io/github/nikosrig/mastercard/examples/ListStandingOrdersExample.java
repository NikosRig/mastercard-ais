package hosting.io.github.nikosrig.mastercard.examples;

import hosting.io.github.nikosrig.mastercard.MastercardAis;
import hosting.io.github.nikosrig.mastercard.model.StandingOrdersList;
import hosting.io.github.nikosrig.mastercard.request.ListStandingOrdersRequest;
import hosting.io.github.nikosrig.mastercard.requestBuilders.ListStandingOrdersRequestBuilder;

public class ListStandingOrdersExample {

	public static void main(String[] args) throws Exception {

		MastercardAis mastercardAis = new MastercardAis.Builder()
				.enableSandboxMode()
				.withPkcs12FilePath("./var/key.p12")
				.withSigningKeyAlias("keyalias")
				.withSigningKeyPassword("keystorepassword")
				.withConsumerKey("nowzQEeE32g6FnESTjKEM6bq2mECwDX2SdOqfL9zae61ec44!5dda6ce1d93d4e2392232d711c4afb3f0000000000000000")
				.build();

		ListStandingOrdersRequest request = new ListStandingOrdersRequestBuilder()
				.withAspspId("420e5cff-0e2a-4156-991a-f6eeef0478cf")
				.withMerchant("MerchantId", "MerchantName")
				.withConsentId("GFiTpF3:EBy5xGqQMatk")
				.withIsLivePsuRequest(true)
				.withPsuAgent("PostmanRuntime/7.20.1")
				.withPsuIPAddress("127.0.0.1")
				.withPsuTppCustomerId("420e5cff-0e2a-4156-991a-f6eeef0478cf")
				.withLimit(20)
				.withAccountId("aa:q648383844dhhfHhTV")
				.withOffset("ofset4prev$earch12345")
				.build();
		StandingOrdersList standingOrdersList = mastercardAis.standingOrders().list(request);
	}
}