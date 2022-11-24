package com.nrigas.mastercard.requestBuilders;

import com.nrigas.mastercard.model.*;
import com.nrigas.mastercard.request.GetConsentRequest;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class GetConsentRequestBuilder extends RequestBuilder {

	private final GetConsentRequestInfo requestInfo;
	private final ArrayList<ConsentPermission> permissions;
	private final ArrayList<ConsentAccount> consentAccounts;
	private LocalDateTime validUntilDateTime = null;

	public GetConsentRequestBuilder() {
		this.permissions = new ArrayList<ConsentPermission>();
		this.consentAccounts = new ArrayList<ConsentAccount>();
		this.requestInfo = new GetConsentRequestInfo();
	}

	@Override
	public void addPsu(
			Boolean isLivePsuRequest,
			String psuAgent,
			String psuIPAddress,
			String psuTppCustomerId
	) {
		this.requestInfo.setPsuTppCustomerId(psuTppCustomerId);
		this.requestInfo.setPsuIPAddress(psuIPAddress);
		this.requestInfo.setPsuAgent(psuAgent);
		this.requestInfo.setLivePsuRequest(isLivePsuRequest);
	}

	@Override
	public void addMerchant(String merchantId, String merchantName) {
		this.requestInfo.setMerchant(new Merchant(merchantId, merchantName));
	}

	@Override
	public void addAspspId(String aspspId) {
		this.requestInfo.setAspspId(aspspId);
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
