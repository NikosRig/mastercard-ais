package io.github.nikosrig.request;

import io.github.nikosrig.request.requestInfo.RequestInfo;
import io.github.nikosrig.requestBuilders.AuthConsentRequestBuilder;

public class AuthConsentRequest {

	public final RequestInfo requestInfo;
	public final String authorization;

	public AuthConsentRequest(AuthConsentRequestBuilder builder) {
		this.requestInfo = builder.requestInfo;
		this.authorization = builder.authorization;
	}
}
