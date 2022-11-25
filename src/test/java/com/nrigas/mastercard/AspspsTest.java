package com.nrigas.mastercard;

import com.nrigas.mastercard.model.AspspList;
import com.nrigas.mastercard.request.ListAspspsRequest;
import com.nrigas.mastercard.request.model.AdditionalData;
import com.nrigas.mastercard.requestBuilders.ListAspspsRequestBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.net.http.HttpResponse;

import static org.mockito.ArgumentMatchers.any;

public class AspspsTest extends TestCase {

	private Aspsps aspsps;

	@Before
	public void setUp() {
		super.setUp();
		this.aspsps = new Aspsps(this.mastercardAisClient);
	}

	@Test
	public void testListShouldParseResponse() throws Exception {
		this.mockListResponse();
		ListAspspsRequest request = new ListAspspsRequestBuilder().build();
		AspspList aspspList = this.aspsps.list(request);

		Assert.assertNotNull("offset", aspspList);
		Assert.assertNotNull("aspsps", aspspList);
	}

	@Test
	public void testListShouldAddParams() throws Exception {
		this.mockListResponse();
		ListAspspsRequest request = new ListAspspsRequestBuilder()
				.withId("123e4567-e89b-12d3-a456-426655440000")
				.withName("Wood bank")
				.withLimit(20)
				.withOffset("offset")
				.addAdditionalData(AdditionalData.capabilities)
				.addAdditionalData(AdditionalData.credentials)
				.addAdditionalData(AdditionalData.health)
				.addAdditionalData(AdditionalData.logo)
				.build();
		this.aspsps.list(request);

		this.assertRequestHas("name");
		this.assertRequestHas("limit");
		this.assertRequestHas("offset");
		this.assertRequestHas("returnAdditionalData");
	}

	private void mockListResponse() throws Exception {
		String responseBody = "{\"originalRequestInfo\":{\"xRequestId\":\"444e4567-e55b-12d3-a456-426655448888\"},\"aspsps\":[{\"id\":\"123e4567-e89b-12d3-a456-426655440000\",\"name\":\"Wood bank\",\"aspspServices\":[\"AIS\",\"PIS\",\"COF\"],\"profile\":\"CMA9\",\"country\":\"UK\",\"capabilities\":{\"retrieve_accounts\":true,\"obtain_ais_consent\":true,\"delete_ais_consent\":true},\"logo\":{\"fileType\":\"png\",\"binaryContent\":\"GNhcm5hbCBwbGVhc3VyZS4=\",\"logoUrl\":\"https://openbanking.mastercard.eu/live/imgs/16f0f635-9d94-4976-b49b-584ca231c232.svg\"},\"health\":{\"aisStatus\":\"LIVE\",\"pisStatus\":\"LIVE\",\"lastUpdatedAt\":\"2021-05-21T08:30:00.123Z\"},\"credentialFields\":[{\"id\":\"iban\",\"displayName\":\"IBAN\"}]}],\"offset\":\"d0868dd0-d070-4ffe-9dde-4f9a278d4761\"}";
		HttpResponse response = this.mockHttpResponse(responseBody, 200);
		Mockito.when(this.mastercardAisClient.postJson(any(), any())).thenReturn(response);
	}
}
