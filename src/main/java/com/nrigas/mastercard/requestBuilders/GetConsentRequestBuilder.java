package com.nrigas.mastercard.requestBuilders;

import com.nrigas.mastercard.model.*;
import com.nrigas.mastercard.request.GetConsentRequest;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class GetConsentRequestBuilder extends RequestBuilder {

	private GetConsentRequestInfo requestInfo = new GetConsentRequestInfo();
	private final ArrayList<ConsentPermission> permissions;
	private final ArrayList<ConsentAccount> consentAccounts;
	private LocalDateTime validUntilDateTime = null;

	public GetConsentRequestBuilder() {
		super();
		this.permissions = new ArrayList<ConsentPermission>();
		this.consentAccounts = new ArrayList<ConsentAccount>();
	}

	public void addTppRedirectURI(String tppRedirectURI) {
		this.requestInfo.setTppRedirectURI(tppRedirectURI);
	}

	public void addConsentPermission(ConsentPermission consentPermission) {
		this.permissions.add(consentPermission);
	}

	public void addCredentials(String iban) {
		this.requestInfo.setCredentials(new Credentials(iban));
	}

	public void addValidUntilDateTime(LocalDateTime validUntilDateTime) {
		this.validUntilDateTime = validUntilDateTime;
	}

	public GetConsentRequestBuilder addAccount(String identification, String currency, String schemeName) {
		AccountReference accountReference = new AccountReference(
				new AccountNumber(identification, schemeName),
				currency
		);
		this.consentAccounts.add(new ConsentAccount(accountReference));
		return this;
	}

	public GetConsentRequest build() {
		return new GetConsentRequest(
				this.requestInfo,
				this.validUntilDateTime,
				this.permissions,
				this.consentAccounts
		);
	}
}
