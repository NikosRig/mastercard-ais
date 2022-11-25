package com.nrigas.mastercard.request;

import com.nrigas.mastercard.request.requestInfo.RequestInfo;
import com.nrigas.mastercard.requestBuilders.ListTransactionsRequestBuilder;

public class ListTransactionsRequest {

	public final RequestInfo requestInfo;
	public final Integer limit;
	public final String offset;
	public final String bookingDateFrom;
	public final String bookingDateTo;
	public final String accountId;

	public ListTransactionsRequest(ListTransactionsRequestBuilder builder) {
		this.requestInfo = builder.requestInfo;
		this.accountId = builder.accountId;
		this.limit = builder.limit;
		this.offset = builder.offset;
		this.bookingDateFrom = builder.bookingDateFrom;
		this.bookingDateTo = builder.bookingDateTo;
	}
}
