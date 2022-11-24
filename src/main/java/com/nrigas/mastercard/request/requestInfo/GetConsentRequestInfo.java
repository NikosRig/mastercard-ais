package com.nrigas.mastercard.request.requestInfo;

public class GetConsentRequestInfo extends RequestInfoImpl {

	public String tppRedirectURI;
	public Credentials credentials;

	public void setTppRedirectURI(String tppRedirectURI) {
		this.tppRedirectURI = tppRedirectURI;
	}

	public void setCredentials(Credentials credentials) {
		this.credentials = credentials;
	}
}
