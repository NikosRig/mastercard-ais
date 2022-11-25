package com.nrigas.mastercard.request;

import com.nrigas.mastercard.request.requestInfo.RequestInfo;
import com.nrigas.mastercard.requestBuilders.GetTransactionRequestBuilder;

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
