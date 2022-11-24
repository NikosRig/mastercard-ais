package com.nrigas.mastercard;

import com.nrigas.mastercard.http.MastercardAisClient;
import com.nrigas.mastercard.model.Transaction;
import com.nrigas.mastercard.request.GetTransactionRequest;
import com.nrigas.mastercard.requestBuilders.GetTransactionRequestBuilder;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.net.http.HttpResponse;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;

public class TransactionsTest extends TestCase {

	private MastercardAisClient mastercardAisClient;
	private Transactions transactions;

	@Before
	public void setUp() {
		this.mastercardAisClient = Mockito.mock(MastercardAisClient.class);
		this.transactions = new Transactions(this.mastercardAisClient);
	}

	@Test
	public void testGetTransactionRequestParams() throws Exception {
		this.mockGetResponse();
		GetTransactionRequest request = new GetTransactionRequestBuilder()
				.withConsentId("MatkBJbqtZ8sPNznYtfV:5g")
				.withMerchant("MerchantId", "MerchantName")
				.withPsuAgent("PostmanRuntime/7.20.1")
				.withPsuIPAddress("217.0.0.1")
				.withPsuTppCustomerId("420e5cff-0e2a-4156-991a-f6eeef0478cf")
				.withIsLivePsuRequest(true)
				.withAspspId("b806ae68-a45b-49d6-b25a-69fdb81dede6")
				.withAccountId("qqCfw:XwAa:665hs5:r55d")
				.withTransactionId("7ccs6s5:r55a:4MctP")
				.build();
		this.transactions.get(request);

		this.assertRequestInfoHas("xRequestId");
		this.assertRequestInfoHas("consentId");
		this.assertRequestInfoHas("aspspId");
		this.assertRequestInfoHas("isLivePsuRequest");
		this.assertRequestInfoHas("psuTppCustomerId");
		this.assertRequestInfoHas("psuIPAddress");
		this.assertRequestInfoHas("psuAgent");
		this.assertRequestInfoHas("merchant");
	}

	@Test
	public void testGetTransactionResponseParams() throws Exception {
		this.mockGetResponse();
		GetTransactionRequest request = new GetTransactionRequestBuilder()
				.withConsentId("MatkBJbqtZ8sPNznYtfV:5g")
				.withMerchant("MerchantId", "MerchantName")
				.withPsuAgent("PostmanRuntime/7.20.1")
				.withPsuIPAddress("217.0.0.1")
				.withPsuTppCustomerId("420e5cff-0e2a-4156-991a-f6eeef0478cf")
				.withIsLivePsuRequest(true)
				.withAspspId("b806ae68-a45b-49d6-b25a-69fdb81dede6")
				.withAccountId("qqCfw:XwAa:665hs5:r55d")
				.withTransactionId("7ccs6s5:r55a:4MctP")
				.build();
		Transaction transaction = this.transactions.get(request);

		Assert.assertNotNull(transaction.transactionId);
		Assert.assertNotNull(transaction.bookingDateTime);
		Assert.assertNotNull(transaction.remittanceInformationUnstructured);
		Assert.assertNotNull(transaction.status);
		Assert.assertNotNull(transaction.creditDebitIndicator);
		Assert.assertNotNull(transaction.tradeDate);
		Assert.assertNotNull(transaction.senderAccountNumber);
		Assert.assertNotNull(transaction.recipientAccountNumber);
		Assert.assertNotNull(transaction.recipientAccountMassPayment);
		Assert.assertNotNull(transaction.recipientBankBicOrSwift);
		Assert.assertNotNull(transaction.recipientBankName);
		Assert.assertNotNull(transaction.recipientBankCode);
		Assert.assertNotNull(transaction.recipientBankCountryCode);
		Assert.assertNotNull(transaction.senderAccountMassPayment);
		Assert.assertNotNull(transaction.senderBankBicOrSwift);
		Assert.assertNotNull(transaction.senderBankName);
		Assert.assertNotNull(transaction.senderBankCode);
		Assert.assertNotNull(transaction.senderBankCountryCode);
		Assert.assertNotNull(transaction.transactionType);
		Assert.assertNotNull(transaction.auxData);
		Assert.assertNotNull(transaction.postTransactionBalance);
		Assert.assertNotNull(transaction.mcc);
		Assert.assertNotNull(transaction.rejectionReason);
		Assert.assertNotNull(transaction.rejectionDate);
		Assert.assertNotNull(transaction.holdExpirationDate);
		Assert.assertNotNull(transaction.senderName);
		Assert.assertNotNull(transaction.recipientName);
		Assert.assertNotNull(transaction.senderAccountNumberScheme);
		Assert.assertNotNull(transaction.recipientAccountNumberScheme);
		Assert.assertNotNull(transaction.transactionAmount);
		Assert.assertNotNull(transaction.initiatorNameAddress);
		Assert.assertNotNull(transaction.senderNameAddress);
		Assert.assertNotNull(transaction.recipientNameAddress);
		Assert.assertNotNull(transaction.recipientBankAddress);
		Assert.assertNotNull(transaction.senderBankAddress);
	}

	private void assertRequestInfoHas(String key) throws Exception {
		Mockito.verify(this.mastercardAisClient).postJson(any(), argThat(jsonBody -> {
			JSONObject requestInfo = new JSONObject(jsonBody).getJSONObject("requestInfo");
			return requestInfo.has(key);
		}));
	}

	private void assertRequestInfoNotHas(String key) throws Exception {
		Mockito.verify(this.mastercardAisClient).postJson(any(), argThat(jsonBody -> {
			JSONObject requestInfo = new JSONObject(jsonBody).getJSONObject("requestInfo");
			return !requestInfo.has(key);
		}));
	}

	private void mockGetResponse() throws Exception {
		String responseBody = "{\"originalRequestInfo\":{\"xRequestId\":\"444e4567-e55b-12d3-a456-426655448888\"},\"transactionId\":\"transactionreference\",\"bookingDateTime\":\"2021-05-21T08:30:00Z\",\"transactionAmount\":{\"currency\":\"USD\",\"amount\":100.23},\"remittanceInformationUnstructured\":\"Payment for fruits\",\"status\":\"ACSC\",\"creditDebitIndicator\":\"CREDIT\",\"initiatorNameAddress\":[\"Street Street\"],\"senderNameAddress\":[\"Street Street\"],\"recipientNameAddress\":[\"Street Street\"],\"tradeDate\":\"2021-05-21T08:30:00Z\",\"senderAccountNumber\":\"ACCNUMBR123456\",\"recipientAccountNumber\":\"ACCNUMBR1234567\",\"recipientAccountMassPayment\":\"RecipientMass1\",\"recipientBankBicOrSwift\":\"RBICCOD1\",\"recipientBankName\":\"Recipient Bank Name 1\",\"recipientBankCode\":\"88457329\",\"recipientBankCountryCode\":\"PL\",\"recipientBankAddress\":[\"Street Street\"],\"senderAccountMassPayment\":\"SenderMass1\",\"senderBankBicOrSwift\":\"SBICCOD1\",\"senderBankName\":\"Sender Bank Name 1\",\"senderBankCode\":\"10901014\",\"senderBankCountryCode\":\"PL\",\"senderBankAddress\":[\"Street Street\"],\"transactionType\":\"Transaction Type 1\",\"auxData\":\"{\\\"additionalProp1\\\": \\\"somePropertyValue1\\\"}\",\"postTransactionBalance\":100.23,\"mcc\":\"MCC1\",\"rejectionReason\":\"Rejection Reason 1\",\"rejectionDate\":\"2021-05-21T08:30:00Z\",\"holdExpirationDate\":\"2021-05-21T08:30:00Z\",\"senderName\":\"John Doe\",\"recipientName\":\"John Doe\",\"senderAccountNumberScheme\":\"IBAN\",\"recipientAccountNumberScheme\":\"IBAN\"}";
		HttpResponse response = this.mockHttpResponse(responseBody, 200);
		Mockito.when(this.mastercardAisClient.postJson(any(), any())).thenReturn(response);
	}
}
