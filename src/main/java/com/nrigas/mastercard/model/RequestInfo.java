package com.nrigas.mastercard.model;

public class RequestInfo {

	public String xRequestId;
	public String aspspId;
	public String psuIPAddress;
	public String psuAgent;
	public String psuTppCustomerId;
	public Merchant merchant;
	public Boolean isLivePsuRequest;

	public void setPsuIPAddress(String psuIPAddress) {
		this.psuIPAddress = psuIPAddress;
	}

	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}

	public void setPsuAgent(String psuAgent) {
		this.psuAgent = psuAgent;
	}

	public void setAspspId(String aspspId) {
		this.aspspId = aspspId;
	}

	public void setxRequestId(String xRequestId) {
		this.xRequestId = xRequestId;
	}

	public void setLivePsuRequest(Boolean livePsuRequest) {
		isLivePsuRequest = livePsuRequest;
	}

	public void setPsuTppCustomerId(String psuTppCustomerId) {
		this.psuTppCustomerId = psuTppCustomerId;
	}
}
