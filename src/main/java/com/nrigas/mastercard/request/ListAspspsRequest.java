package com.nrigas.mastercard.request;

import com.nrigas.mastercard.request.model.AdditionalData;
import com.nrigas.mastercard.request.requestInfo.RequestInfo;
import com.nrigas.mastercard.requestBuilders.ListAspspsRequestBuilder;

import java.util.ArrayList;

public class ListAspspsRequest {

	public final RequestInfo requestInfo;
	public final String id;
	public final String name;
	public final String country;
	public final ArrayList<AdditionalData> returnAdditionalData;
	public final Integer limit;
	public final String offset;

	public ListAspspsRequest(ListAspspsRequestBuilder builder) {
		this.requestInfo = builder.requestInfo;
		this.id = builder.id;
		this.name = builder.name;
		this.country = builder.country;
		this.returnAdditionalData = builder.returnAdditionalData;
		this.limit = builder.limit;
		this.offset = builder.offset;
	}
}
