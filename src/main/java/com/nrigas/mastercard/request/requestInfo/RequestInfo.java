package com.nrigas.mastercard.request.requestInfo;

import java.util.UUID;

public class RequestInfo {

	public String xRequestId;
	public String aspspId;
	public String psuIPAddress;
	public String psuAgent;
	public String psuTppCustomerId;
	public Merchant merchant;
	public Boolean isLivePsuRequest;
	public String consentId;

	public RequestInfo() {
		this.xRequestId = UUID.randomUUID().toString();
	}

	public void setxRequestId(String xRequestId) {
		this.xRequestId = xRequestId;
	}
}
