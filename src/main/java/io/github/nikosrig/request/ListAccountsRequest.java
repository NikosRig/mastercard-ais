package io.github.nikosrig.request;

import io.github.nikosrig.request.requestInfo.RequestInfo;
import io.github.nikosrig.requestBuilders.ListAccountsRequestBuilder;

public class ListAccountsRequest {

	public final RequestInfo requestInfo;

	public ListAccountsRequest(ListAccountsRequestBuilder builder) {
		this.requestInfo = builder.requestInfo;
	}
}
