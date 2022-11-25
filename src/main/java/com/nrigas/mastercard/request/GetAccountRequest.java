package com.nrigas.mastercard.request;

import com.nrigas.mastercard.request.requestInfo.GetAccountRequestInfo;
import com.nrigas.mastercard.requestBuilders.GetAccountRequestBuilder;

public class GetAccountRequest {

	public final String accountId;
	public final GetAccountRequestInfo requestInfo;

	public GetAccountRequest(GetAccountRequestBuilder builder) {
		this.accountId = builder.accountId;
		this.requestInfo = builder.requestInfo;
	}
}
