package com.nrigas.mastercard.request;

import com.nrigas.mastercard.model.GetAccountRequestInfo;

public class GetAccountRequest {

	public final String accountId;
	public final GetAccountRequestInfo requestInfo;

	public GetAccountRequest(GetAccountRequestInfo requestInfo, String accountId) {
		this.accountId = accountId;
		this.requestInfo = requestInfo;
	}
}