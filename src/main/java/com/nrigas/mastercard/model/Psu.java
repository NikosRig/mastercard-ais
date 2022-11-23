package com.nrigas.mastercard.model;

import java.util.Optional;

public class Psu {

	private final Boolean isLivePsuRequest;
	private final String ipAddress;
	private final String agent;
	private final String tppCustomerId;

	public Psu(
			Boolean isLivePsuRequest,
			Optional<String> ipAddress,
			Optional<String> agent,
			Optional<String> tppCustomerId
	) {
		this.isLivePsuRequest = isLivePsuRequest;
		this.ipAddress = ipAddress.orElse(null);
		this.agent = agent.orElse(null);
		this.tppCustomerId = tppCustomerId.orElse(null);
	}

	public Boolean isLivePsuRequest() {
		return isLivePsuRequest;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public String getTppCustomerId() {
		return tppCustomerId;
	}

	public String getAgent() {
		return agent;
	}
}
