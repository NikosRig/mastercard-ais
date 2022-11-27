package io.github.nikosrig.request;

import io.github.nikosrig.request.requestInfo.RequestInfo;
import io.github.nikosrig.requestBuilders.ListStandingOrdersRequestBuilder;

public class ListStandingOrdersRequest {

	public final RequestInfo requestInfo;
	public final String accountId;
	public final String offset;
	public final Integer limit;

	public ListStandingOrdersRequest(ListStandingOrdersRequestBuilder builder) {
		this.requestInfo = builder.requestInfo;
		this.accountId = builder.accountId;
		this.offset = builder.offset;
		this.limit = builder.limit;
	}
}
