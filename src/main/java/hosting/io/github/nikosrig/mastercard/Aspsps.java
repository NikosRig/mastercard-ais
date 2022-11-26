package hosting.io.github.nikosrig.mastercard;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import hosting.io.github.nikosrig.mastercard.http.MastercardAisClient;
import hosting.io.github.nikosrig.mastercard.model.AspspList;
import hosting.io.github.nikosrig.mastercard.request.ListAspspsRequest;

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
