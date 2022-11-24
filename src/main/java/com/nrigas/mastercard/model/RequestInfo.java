package com.nrigas.mastercard.model;

public interface RequestInfo {
	public void setPsuIPAddress(String psuIPAddress);

	public void setMerchant(Merchant merchant);

	public void setPsuAgent(String psuAgent);

	public void setAspspId(String aspspId);

	public void setxRequestId(String xRequestId);

	public void setLivePsuRequest(Boolean livePsuRequest);

	public void setPsuTppCustomerId(String psuTppCustomerId);
}
