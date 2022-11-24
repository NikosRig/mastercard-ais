package com.nrigas.mastercard.request;

import com.nrigas.mastercard.request.requestInfo.RequestInfoImpl;

public class AuthConsentRequest {

	public final RequestInfoImpl requestInfo;
	public final String authorization;

	public AuthConsentRequest(RequestInfoImpl requestInfo, String authorization) {
		this.requestInfo = requestInfo;
		this.authorization = authorization;
	}
}
