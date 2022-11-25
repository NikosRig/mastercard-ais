package com.nrigas.mastercard.request;

import com.nrigas.mastercard.request.requestInfo.RequestInfo;
import com.nrigas.mastercard.requestBuilders.GetRawConsentRequestBuilder;

public class GetRawConsentRequest {

	public final RequestInfo requestInfo;

	public GetRawConsentRequest(GetRawConsentRequestBuilder builder) {
		this.requestInfo = builder.requestInfo;
	}
}
