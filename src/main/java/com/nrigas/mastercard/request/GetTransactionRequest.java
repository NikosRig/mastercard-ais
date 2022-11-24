package com.nrigas.mastercard.request;

import com.nrigas.mastercard.request.requestInfo.TransactionRequestInfo;
import com.nrigas.mastercard.requestBuilders.GetTransactionRequestBuilder;

public class GetTransactionRequest {

	public final TransactionRequestInfo requestInfo;
	public final String accountId;
	public final String transactionId;

	public GetTransactionRequest(GetTransactionRequestBuilder builder) {
		this.requestInfo = builder.requestInfo;
		this.accountId = builder.accountId;
		this.transactionId = builder.transactionId;
	}
}
