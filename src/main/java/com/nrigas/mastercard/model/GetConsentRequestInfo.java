package com.nrigas.mastercard.model;

public class GetConsentRequestInfo extends RequestInfo {

	public String tppRedirectURI;
	public Credentials credentials;

	public void setTppRedirectURI(String tppRedirectURI) {
		this.tppRedirectURI = tppRedirectURI;
	}

	public void setCredentials(Credentials credentials) {
		this.credentials = credentials;
	}
}
