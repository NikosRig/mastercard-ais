package hosting.io.github.nikosrig.mastercard.request;

import hosting.io.github.nikosrig.mastercard.request.requestInfo.RequestInfo;
import hosting.io.github.nikosrig.mastercard.requestBuilders.GetRawConsentRequestBuilder;

public class GetRawConsentRequest {

	public final RequestInfo requestInfo;

	public GetRawConsentRequest(GetRawConsentRequestBuilder builder) {
		this.requestInfo = builder.requestInfo;
	}
}
