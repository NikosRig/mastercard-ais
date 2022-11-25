package com.nrigas.mastercard.request;

import com.nrigas.mastercard.request.requestInfo.RequestInfo;

public class ListAccountsRequest {

	public final RequestInfo requestInfo;

	public ListAccountsRequest(RequestInfo requestInfo) {
		this.requestInfo = requestInfo;
	}
}
