package io.github.nikosrig.examples;

import io.github.nikosrig.MastercardAis;
import io.github.nikosrig.model.Consent;
import io.github.nikosrig.request.GetConsentRequest;
import io.github.nikosrig.request.requestInfo.ConsentPermission;
import io.github.nikosrig.requestBuilders.GetConsentRequestBuilder;

import java.time.LocalDateTime;

public class ConsentsExample {

	public static void main(String[] args) throws Exception {

		try{
			MastercardAis mastercardAis = new MastercardAis.Builder()
					.enableSandboxMode()
					.withPkcs12FilePath("./var/key.p12")
					.withSigningKeyAlias("keyalias")
					.withSigningKeyPassword("keystorepassword")
					.withConsumerKey("a5aef36d44f33a6fe5a3feeb5f93d0000000000000000")
					.build();
			GetConsentRequest request = new GetConsentRequestBuilder()
					.withAspspId("420e5cff-0e2a-4156-991a-f6eeef0478cf")
					.withMerchant("MerchantId", "MerchantName")
					.withIsLivePsuRequest(true)
					.withPsuAgent("PostmanRuntime/7.20.1")
					.withPsuIPAddress("127.0.0.1")
					.withPsuTppCustomerId("420e5cff-0e2a-4156-991a-f6eeef0478cf")
					.addConsentPermission(ConsentPermission.allPSD2)
					.addAccount("ACCNUMBR1234567", "EUR", "IBAN")
					.withTppRedirectURI("https://tpp-ob.com/callback")
					.withValidUntilDateTime(LocalDateTime.now())
					.withCredentials("DE357543513")
					.build();
			Consent consent = mastercardAis.consents().get(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
