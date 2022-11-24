package com.nrigas.mastercard.requestBuilders;

import com.nrigas.mastercard.model.*;
import com.nrigas.mastercard.request.GetConsentRequest;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class ConsentRequestBuilder extends RequestBuilder {

	private final GetConsentRequestInfo requestInfo;
	private final ArrayList<ConsentPermission> permissions;
	private final ArrayList<ConsentAccount> consentAccounts;
	private LocalDateTime validUntilDateTime = null;

	public ConsentRequestBuilder() {
		this.permissions = new ArrayList<ConsentPermission>();
		this.consentAccounts = new ArrayList<ConsentAccount>();
		this.requestInfo = new GetConsentRequestInfo();
	}

	@Override
	public ConsentRequestBuilder withPsu(
			Boolean isLivePsuRequest,
			String psuAgent,
			String psuIPAddress,
			String psuTppCustomerId
	) {
		this.requestInfo.setPsuTppCustomerId(psuTppCustomerId);
		this.requestInfo.setPsuIPAddress(psuIPAddress);
		this.requestInfo.setPsuAgent(psuAgent);
		this.requestInfo.setLivePsuRequest(isLivePsuRequest);

		return this;
	}

	@Override
	public ConsentRequestBuilder withMerchant(String merchantId, String merchantName) {
		this.requestInfo.setMerchant(new Merchant(merchantId, merchantName));
		return this;
	}

	@Override
	public ConsentRequestBuilder withAspspId(String aspspId) {
		this.requestInfo.setAspspId(aspspId);
		return this;
	}

	public ConsentRequestBuilder withTppRedirectURI(String tppRedirectURI) {
		this.requestInfo.setTppRedirectURI(tppRedirectURI);
		return this;
	}

	public ConsentRequestBuilder addConsentPermission(ConsentPermission consentPermission) {
		this.permissions.add(consentPermission);
		return this;
	}

	public ConsentRequestBuilder withCredentials(String iban) {
		this.requestInfo.setCredentials(new Credentials(iban));
		return this;
	}

	public ConsentRequestBuilder withValidUntilDateTime(LocalDateTime validUntilDateTime) {
		this.validUntilDateTime = validUntilDateTime;
		return this;
	}

	public ConsentRequestBuilder addAccount(String identification, String currency, String schemeName) {
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
