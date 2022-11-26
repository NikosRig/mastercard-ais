package hosting.io.github.nikosrig.mastercard.request;

import hosting.io.github.nikosrig.mastercard.request.requestInfo.RequestInfo;
import hosting.io.github.nikosrig.mastercard.requestBuilders.AuthConsentRequestBuilder;

public class AuthConsentRequest {

	public final RequestInfo requestInfo;
	public final String authorization;

	public AuthConsentRequest(AuthConsentRequestBuilder builder) {
		this.requestInfo = builder.requestInfo;
		this.authorization = builder.authorization;
	}
}
