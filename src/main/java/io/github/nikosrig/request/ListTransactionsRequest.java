package io.github.nikosrig.request;

import io.github.nikosrig.request.requestInfo.RequestInfo;
import io.github.nikosrig.requestBuilders.ListTransactionsRequestBuilder;

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
