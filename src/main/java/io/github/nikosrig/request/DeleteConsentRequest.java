package io.github.nikosrig.request;

import io.github.nikosrig.request.requestInfo.RequestInfo;
import io.github.nikosrig.requestBuilders.DeleteConsentRequestBuilder;

public class DeleteConsentRequest {

	public final RequestInfo requestInfo;
	public final String consentId;

	public DeleteConsentRequest(DeleteConsentRequestBuilder builder) {
		this.requestInfo = builder.requestInfo;
		this.consentId = builder.consentId;
	}
}
