package com.nrigas.mastercard.request;

import com.nrigas.mastercard.request.requestInfo.RequestInfo;

public class GetRawConsentRequest {

	public final RequestInfo requestInfo;

	public GetRawConsentRequest(RequestInfo requestInfo) {
		this.requestInfo = requestInfo;
	}
}
