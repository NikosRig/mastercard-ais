package hosting.io.github.nikosrig.mastercard.request;

import hosting.io.github.nikosrig.mastercard.request.requestInfo.RequestInfo;
import hosting.io.github.nikosrig.mastercard.requestBuilders.DeleteConsentRequestBuilder;

public class DeleteConsentRequest {

	public final RequestInfo requestInfo;
	public final String consentId;

	public DeleteConsentRequest(DeleteConsentRequestBuilder builder) {
		this.requestInfo = builder.requestInfo;
		this.consentId = builder.consentId;
	}
}
