package com.nrigas.mastercard;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.nrigas.mastercard.http.MastercardAisClient;
import com.nrigas.mastercard.request.GetAccountRequest;
import com.nrigas.mastercard.response.GetAccountResponse;

import java.net.http.HttpResponse;

public class Accounts extends MastercardAisService {

	private final Gson gson;

	public Accounts(MastercardAisClient client) {
		super(client);
		this.gson = new GsonBuilder().create();
	}

	public GetAccountResponse get(GetAccountRequest request) throws Exception {

		JsonObject payload = new JsonObject();
		payload.add("accountId", new JsonPrimitive(request.accountId));
		payload.add("requestInfo", this.gson.toJsonTree(request.requestInfo));

		HttpResponse<String> response = this.client.postJson(
				"/openbanking/connect/api/accounts/account",
				payload.toString()
		);

		return new GsonBuilder().create()
				.fromJson(response.body(), GetAccountResponse.class);
	}
}
