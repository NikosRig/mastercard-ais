package com.nrigas.mastercard.requestBuilders;

import com.nrigas.mastercard.model.Psu;

import java.util.Optional;

abstract public class RequestBuilder {

	protected Psu psu;
	protected String aspsId;

	public RequestBuilder withPsu(Psu psu) {
		this.psu = psu;
		return this;
	}

	protected RequestBuilder withPsu(
			Boolean isLivePsuRequest,
			String psuAgent,
			String psuIpAddress,
			String psuTppCustomerId
	) {
		this.psu = new Psu(
				isLivePsuRequest,
				Optional.ofNullable(psuIpAddress),
				Optional.ofNullable(psuAgent),
				Optional.ofNullable(psuTppCustomerId)
		);
		return this;
	};

	protected RequestBuilder withAspsId(String aspsId) {
		this.aspsId = aspsId;
		return this;
	}
}
