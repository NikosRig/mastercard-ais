package com.nrigas.mastercard.request;

import com.nrigas.mastercard.request.requestInfo.RequestInfo;
import com.nrigas.mastercard.requestBuilders.AuthConsentRequestBuilder;

public class AuthConsentRequest {

	public final RequestInfo requestInfo;
	public final String authorization;

	public AuthConsentRequest(AuthConsentRequestBuilder builder) {
		this.requestInfo = builder.requestInfo;
		this.authorization = builder.authorization;
	}
}
