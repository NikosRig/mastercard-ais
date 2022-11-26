package hosting.io.github.nikosrig.mastercard;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import hosting.io.github.nikosrig.mastercard.http.MastercardAisClient;
import hosting.io.github.nikosrig.mastercard.model.StandingOrdersList;
import hosting.io.github.nikosrig.mastercard.request.ListStandingOrdersRequest;

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
