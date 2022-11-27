package io.github.nikosrig.request;

import io.github.nikosrig.request.requestInfo.GetAccountRequestInfo;
import io.github.nikosrig.requestBuilders.GetAccountRequestBuilder;

public class GetAccountRequest {

	public final String accountId;
	public final GetAccountRequestInfo requestInfo;

	public GetAccountRequest(GetAccountRequestBuilder builder) {
		this.accountId = builder.accountId;
		this.requestInfo = builder.requestInfo;
	}
}
