package io.github.nikosrig.request;

import io.github.nikosrig.request.requestInfo.RequestInfo;
import io.github.nikosrig.requestBuilders.GetBalanceRequestBuilder;

public class GetBalanceRequest {

	public final String accountId;
	public RequestInfo requestInfo;

	public GetBalanceRequest(GetBalanceRequestBuilder builder) {
		this.requestInfo = builder.requestInfo;
		this.accountId = builder.accountId;
	}
}
