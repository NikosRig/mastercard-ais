package io.github.nikosrig.request;

import io.github.nikosrig.request.requestInfo.RequestInfo;
import io.github.nikosrig.requestBuilders.GetTransactionRequestBuilder;

public class GetTransactionRequest {

	public final RequestInfo requestInfo;
	public final String accountId;
	public final String transactionId;

	public GetTransactionRequest(GetTransactionRequestBuilder builder) {
		this.requestInfo = builder.requestInfo;
		this.accountId = builder.accountId;
		this.transactionId = builder.transactionId;
	}
}
