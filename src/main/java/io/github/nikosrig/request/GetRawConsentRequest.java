package io.github.nikosrig.request;

import io.github.nikosrig.request.requestInfo.RequestInfo;
import io.github.nikosrig.requestBuilders.GetRawConsentRequestBuilder;

public class GetRawConsentRequest {

	public final RequestInfo requestInfo;

	public GetRawConsentRequest(GetRawConsentRequestBuilder builder) {
		this.requestInfo = builder.requestInfo;
	}
}
