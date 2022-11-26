package hosting.io.github.nikosrig.mastercard.http;

import com.google.gson.JsonObject;
import hosting.io.github.nikosrig.mastercard.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.net.http.HttpClient;
import java.net.http.HttpResponse;

import static org.mockito.ArgumentMatchers.any;

public class MastercardAisClientTest extends TestCase {

	private MastercardAisClient client;
	private HttpClient httpClient;
	private MastercardAisAuthUtil authUtil;

	@Before
	public void setUp()  {
		this.httpClient = Mockito.mock(HttpClient.class);
		this.authUtil = Mockito.mock(MastercardAisAuthUtil.class);
		this.client = new MastercardAisClient(this.httpClient, this.authUtil, true);
	}

	@Test
	public void testExpectExceptionWhenStatusCodeIsNotOk() throws Exception {
		HttpResponse response = this.mockHttpResponse("test", 404);
		Mockito.when(this.httpClient.send(any(), any())).thenReturn(response);
		Mockito.when(this.authUtil.createAuthHeader(any(), any())).thenReturn("auth");

		Assert.assertThrows(
				Exception.class,
				() -> this.client.postJson("/", new JsonObject().toString())
		);
	}
}
