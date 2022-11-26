package hosting.io.github.nikosrig.mastercard.request;

import hosting.io.github.nikosrig.mastercard.request.requestInfo.RequestInfo;
import hosting.io.github.nikosrig.mastercard.requestBuilders.ListAccountsRequestBuilder;

public class ListAccountsRequest {

	public final RequestInfo requestInfo;

	public ListAccountsRequest(ListAccountsRequestBuilder builder) {
		this.requestInfo = builder.requestInfo;
	}
}
