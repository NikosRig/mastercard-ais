package com.nrigas.mastercard.request;

import com.nrigas.mastercard.request.requestInfo.RequestInfo;
import com.nrigas.mastercard.requestBuilders.GetBalanceRequestBuilder;

public class GetBalanceRequest {

	public final String accountId;
	public RequestInfo requestInfo;

	public GetBalanceRequest(GetBalanceRequestBuilder builder) {
		this.requestInfo = builder.requestInfo;
		this.accountId = builder.accountId;
	}
}
