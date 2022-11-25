package com.nrigas.mastercard.request;

import com.nrigas.mastercard.request.requestInfo.RequestInfo;
import com.nrigas.mastercard.requestBuilders.ListAccountsRequestBuilder;

public class ListAccountsRequest {

	public final RequestInfo requestInfo;

	public ListAccountsRequest(ListAccountsRequestBuilder builder) {
		this.requestInfo = builder.requestInfo;
	}
}
