package com.nrigas.mastercard.service.Account;

import com.google.gson.GsonBuilder;
import com.nrigas.mastercard.http.MastercardAisClient;
import com.nrigas.mastercard.service.Account.request.GetAccountRequest;
import com.nrigas.mastercard.service.Account.response.GetAccountResponse;
import com.nrigas.mastercard.service.MastercardAisService;
import org.json.JSONObject;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import java.net.http.HttpResponse;
import java.util.UUID;

public class Account extends MastercardAisService {

	public Account(MastercardAisClient client) {
		super(client);
	}

	public GetAccountResponse get(GetAccountRequest request) throws Exception {

		JsonObjectBuilder requestInfoBuilder = Json.createObjectBuilder()
				.add("xRequestId", UUID.randomUUID().toString())
				.add("aspspId", request.aspspId)
				.add("consentId", request.consentId);

		this.addPsu(request.psu, requestInfoBuilder);
		this.addMerchant(request.merchant, requestInfoBuilder);

		JsonObject payload = Json.createObjectBuilder()
				.add("requestInfo", requestInfoBuilder)
				.add("accountId", request.accountId)
				.build();

		HttpResponse<String> response = this.client.postJson(
				"/openbanking/connect/api/accounts/account",
				payload.toString()
		);
		JSONObject responsePayload = new JSONObject(response.body());

		return new GsonBuilder().create()
				.fromJson(
				responsePayload.getJSONObject("account").toString(),
				GetAccountResponse.class
		);
	}
}
