package com.nrigas.mastercard;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.nrigas.mastercard.http.MastercardAisClient;
import com.nrigas.mastercard.model.Account;
import com.nrigas.mastercard.model.AccountList;
import com.nrigas.mastercard.request.GetAccountRequest;
import com.nrigas.mastercard.request.GetBalanceRequest;
import com.nrigas.mastercard.request.ListAccountsRequest;
import org.json.JSONObject;

import java.net.http.HttpResponse;

public class Accounts {

	private final Gson gson;
	private final MastercardAisClient client;

	public Accounts(MastercardAisClient client) {
		this.client = client;
		this.gson = new GsonBuilder().create();
	}

	public Account get(GetAccountRequest request) throws Exception {

		JsonObject payload = new JsonObject();
		payload.add("accountId", new JsonPrimitive(request.accountId));
		payload.add("requestInfo", this.gson.toJsonTree(request.requestInfo));

		HttpResponse<String> response = this.client.postJson(
				"/openbanking/connect/api/accounts/account",
				payload.toString()
		);
		JSONObject responsePayload = new JSONObject(response.body());
		String account = responsePayload.getJSONObject("account").toString();

		return new GsonBuilder().create()
				.fromJson(account, Account.class);
	}

	public Account balance(GetBalanceRequest request) throws Exception {

		JsonObject payload = new JsonObject();
		payload.add("accountId", new JsonPrimitive(request.accountId));
		payload.add("requestInfo", this.gson.toJsonTree(request.requestInfo));

		HttpResponse<String> response = this.client.postJson(
				"/openbanking/connect/api/accounts/account/balances",
				payload.toString()
		);
		JSONObject responsePayload = new JSONObject(response.body());
		String account = responsePayload.getJSONObject("account").toString();

		return new GsonBuilder().create()
				.fromJson(account, Account.class);
	}

	public AccountList list(ListAccountsRequest request) throws Exception {

		HttpResponse<String> response = this.client.postJson(
				"/openbanking/connect/api/accounts",
				this.gson.toJson(request)
		);

		return new GsonBuilder().create()
				.fromJson(response.body(), AccountList.class);
	}
}
