package io.github.nikosrig;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.github.nikosrig.http.MastercardAisClient;
import io.github.nikosrig.model.AspspList;
import io.github.nikosrig.request.ListAspspsRequest;

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
				"/openbanking/connect/api/accounts/aspsps",
				this.gson.toJson(request)
		);

		return new GsonBuilder().create()
				.fromJson(response.body(), AspspList.class);
	}
}
