package com.nrigas.mastercard.request;

import com.nrigas.mastercard.request.requestInfo.RequestInfo;
import com.nrigas.mastercard.requestBuilders.ListStandingOrdersRequestBuilder;

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