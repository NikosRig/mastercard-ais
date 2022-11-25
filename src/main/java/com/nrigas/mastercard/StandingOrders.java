package com.nrigas.mastercard;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nrigas.mastercard.http.MastercardAisClient;
import com.nrigas.mastercard.model.StandingOrdersList;
import com.nrigas.mastercard.request.ListStandingOrdersRequest;

import java.net.http.HttpResponse;

public class StandingOrders {
	private final Gson gson;
	private final MastercardAisClient client;

	public StandingOrders(MastercardAisClient client) {
		this.client = client;
		this.gson = new GsonBuilder().create();
	}

	public StandingOrdersList list(ListStandingOrdersRequest request) throws Exception {
		HttpResponse<String> response = this.client.postJson(
				"/openbanking/connect/api/accounts/account/standing-orders",
				this.gson.toJson(request)
		);

		return this.gson.fromJson(response.body(), StandingOrdersList.class);
	}
}
