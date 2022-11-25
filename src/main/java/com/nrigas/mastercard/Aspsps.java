package com.nrigas.mastercard;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nrigas.mastercard.http.MastercardAisClient;
import com.nrigas.mastercard.model.AspspList;
import com.nrigas.mastercard.request.ListAspspsRequest;

import java.net.http.HttpResponse;

public class Aspsps {

	private final MastercardAisClient client;
	private final Gson gson;

	public Aspsps(MastercardAisClient client) {
		this.client = client;
		this.gson = new GsonBuilder().create();
	}

	public AspspList list(ListAspspsRequest request) throws Exception {

		HttpResponse<String> response = this.client.postJson(
				"/openbanking/connect/api/accounts",
				this.gson.toJson(request)
		);

		return new GsonBuilder().create()
				.fromJson(response.body(), AspspList.class);
	}
}
