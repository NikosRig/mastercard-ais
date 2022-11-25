package com.nrigas.mastercard.requestBuilders;

import com.nrigas.mastercard.request.ListAspspsRequest;
import com.nrigas.mastercard.request.model.AdditionalData;
import com.nrigas.mastercard.request.requestInfo.RequestInfo;

import java.util.ArrayList;

public class ListAspspsRequestBuilder {

	public final RequestInfo requestInfo;
	public final ArrayList<AdditionalData> returnAdditionalData;
	public String id;
	public String name;
	public String country;
	public Integer limit;
	public String offset;

	public ListAspspsRequestBuilder() {
		this.requestInfo = new RequestInfo();
		this.returnAdditionalData = new ArrayList<AdditionalData>();
	}

	public ListAspspsRequestBuilder withId(String id) {
		this.id = id;
		return this;
	}

	public ListAspspsRequestBuilder withName(String name) {
		this.name = name;
		return this;
	}

	public ListAspspsRequestBuilder withCountry(String country) {
		this.country = country;
		return this;
	}

	public ListAspspsRequestBuilder withLimit(Integer limit) {
		this.limit = limit;
		return this;
	}

	public ListAspspsRequestBuilder withOffset(String offset) {
		this.offset = offset;
		return this;
	}

	public ListAspspsRequestBuilder addAdditionalData(AdditionalData additionalData) {
		this.returnAdditionalData.add(additionalData);
		return this;
	}

	public ListAspspsRequest build() {
		return new ListAspspsRequest(this);
	}
}
