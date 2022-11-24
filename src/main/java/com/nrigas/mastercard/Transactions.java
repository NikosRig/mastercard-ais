package com.nrigas.mastercard;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.nrigas.mastercard.http.MastercardAisClient;
import com.nrigas.mastercard.model.Transaction;
import com.nrigas.mastercard.request.GetTransactionRequest;

import java.net.http.HttpResponse;

public class Transactions extends MastercardAisService {

	private final Gson gson;

	public Transactions(MastercardAisClient client) {
		super(client);
		this.gson = new GsonBuilder().create();
	}

	public Transaction get(GetTransactionRequest request) throws Exception {
		JsonObject requestBody = new JsonObject();
		requestBody.add("requestInfo", this.gson.toJsonTree(request.requestInfo));
		requestBody.add("accountId", new JsonPrimitive(request.accountId));
		requestBody.add("transactionId", new JsonPrimitive(request.transactionId));

		HttpResponse<String> response = this.client.postJson(
				"/openbanking/connect/api/accounts/account/transactions/transaction",
				requestBody.toString()
		);

		return this.gson.fromJson(response.body(), Transaction.class);
	}
}
