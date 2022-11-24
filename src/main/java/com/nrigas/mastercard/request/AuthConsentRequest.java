package com.nrigas.mastercard.request;

import com.nrigas.mastercard.model.RequestInfo;

public class AuthConsentRequest {

	public final RequestInfo requestInfo;
	public final String authorization;

	public AuthConsentRequest(RequestInfo requestInfo, String authorization) {
		this.requestInfo = requestInfo;
		this.authorization = authorization;
	}
}
