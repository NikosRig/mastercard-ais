package io.github.nikosrig;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import io.github.nikosrig.http.MastercardAisClient;
import io.github.nikosrig.model.Transaction;
import io.github.nikosrig.model.TransactionList;
import io.github.nikosrig.request.GetTransactionRequest;
import io.github.nikosrig.request.ListTransactionsRequest;

import java.net.http.HttpResponse;

public class Transactions {

	private final Gson gson;
	private final MastercardAisClient client;

	public Transactions(MastercardAisClient client) {
		this.client = client;
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

	public TransactionList list(ListTransactionsRequest request) throws Exception {

		HttpResponse<String> response = this.client.postJson(
				"/openbanking/connect/api/accounts/account/transactions",
				this.gson.toJson(request)
		);

		return new GsonBuilder().create()
				.fromJson(response.body(), TransactionList.class);
	}
}
