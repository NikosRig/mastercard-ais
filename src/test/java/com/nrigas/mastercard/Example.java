package com.nrigas.mastercard;

import com.nrigas.mastercard.model.ConsentPermission;
import com.nrigas.mastercard.requestBuilders.AuthConsentRequestBuilder;
import com.nrigas.mastercard.requestBuilders.DeleteConsentRequestBuilder;
import com.nrigas.mastercard.requestBuilders.GetAccountRequestBuilder;
import com.nrigas.mastercard.requestBuilders.GetConsentRequestBuilder;
import com.nrigas.mastercard.service.Account.request.GetAccountRequest;
import com.nrigas.mastercard.service.Account.response.GetAccountResponse;
import com.nrigas.mastercard.service.Consent.request.AuthConsentRequest;
import com.nrigas.mastercard.service.Consent.request.DeleteConsentRequest;
import com.nrigas.mastercard.service.Consent.request.GetConsentRequest;
import com.nrigas.mastercard.service.Consent.response.AuthorizeConsentResponse;
import com.nrigas.mastercard.service.Consent.response.GetConsentResponse;

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
				.withCredentials("DE357543513")
				.addConsentPermission(ConsentPermission.allPSD2)
				.addConsentAccount("ACCNUMBR1234567", "EUR")
				.build();
		GetConsentResponse getConsentResponse = mastercardAis.consent().get(getConsentRequest);

		AuthConsentRequest authConsentRequest = new AuthConsentRequestBuilder()
				.withAspsId("420e5cff-0e2a-4156-991a-f6eeef0478cf")
				.withMerchant("MerchantId", "MerchantName")
				.withAuthCode("code=UKaccountEsbGdTB2a9MbSdt53serRsv0aUK001&state=c4ebdeb0-fdce-4879-ab84-62fdfaa28b80")
				.withPsu(true, "PostmanRuntime/7.20.1", "127.0.0.1")
				.build();
		AuthorizeConsentResponse authConsentResponse = mastercardAis.consent().authorize(authConsentRequest);

		DeleteConsentRequest deleteConsentRequest = new DeleteConsentRequestBuilder()
				.withAspsId("420e5cff-0e2a-4156-991a-f6eeef0478cf")
				.withMerchant("MerchantId", "MerchantName")
				.withPsuTppCustomerId("psuTppCustomerId")
				.withConsentId("GFiTpF3:EBy5xGqQMatk")
				.build();
		mastercardAis.consent().delete(deleteConsentRequest);

		GetAccountRequest getAccountRequest = new GetAccountRequestBuilder()
				.withAspsId("420e5cff-0e2a-4156-991a-f6eeef0478cf")
				.withMerchant("MerchantId", "MerchantName")
				.withConsentId("GFiTpF3:EBy5xGqQMatk")
				.withAccountId("aa:q648383844dhhfHhTV")
				.withPsu(true, "PostmanRuntime/7.20.1", "127.0.0.1", null)
				.build();
		GetAccountResponse getAccountResponse = mastercardAis.account().get(getAccountRequest);
	}
}