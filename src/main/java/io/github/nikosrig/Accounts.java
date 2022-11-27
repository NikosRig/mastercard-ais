package io.github.nikosrig;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import io.github.nikosrig.http.MastercardAisClient;
import io.github.nikosrig.model.Account;
import io.github.nikosrig.model.AccountList;
import io.github.nikosrig.request.GetAccountRequest;
import io.github.nikosrig.request.GetBalanceRequest;
import io.github.nikosrig.request.ListAccountsRequest;
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
