package com.nrigas.mastercard.requestBuilders;

import com.nrigas.mastercard.model.AccountNumber;
import com.nrigas.mastercard.request.GetConsentRequest;
import com.nrigas.mastercard.request.requestInfo.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class GetConsentRequestBuilder {

	private final GetConsentRequestInfo requestInfo;
	private final ArrayList<ConsentPermission> permissions;
	private final ArrayList<ConsentAccount> consentAccounts;
	private LocalDateTime validUntilDateTime = null;

	public GetConsentRequestBuilder() {
		this.requestInfo = new GetConsentRequestInfo();
		this.permissions = new ArrayList<ConsentPermission>();
		this.consentAccounts = new ArrayList<ConsentAccount>();
	}

	public GetConsentRequestBuilder withPsuAgent(String psuAgent) {
		this.requestInfo.psuAgent = psuAgent;
		return this;
	};

	public GetConsentRequestBuilder withPsuIPAddress(String psuIPAddress) {
		this.requestInfo.psuIPAddress = psuIPAddress;
		return this;
	};

	public GetConsentRequestBuilder withIsLivePsuRequest(Boolean isLivePsuRequest) {
		this.requestInfo.isLivePsuRequest = isLivePsuRequest;
		return this;
	};

	public GetConsentRequestBuilder withPsuTppCustomerId(String psuTppCustomerId) {
		this.requestInfo.psuTppCustomerId = psuTppCustomerId;
		return this;
	};

	public GetConsentRequestBuilder withAspspId(String aspspId) {
		this.requestInfo.aspspId = aspspId;
		return this;
	}

	public GetConsentRequestBuilder withMerchant(String merchantId, String merchantName) {
		this.requestInfo.merchant = new Merchant(merchantId, merchantName);
		return this;
	}

	public GetConsentRequestBuilder withTppRedirectURI(String tppRedirectURI) {
		this.requestInfo.setTppRedirectURI(tppRedirectURI);
		return this;
	}

	public GetConsentRequestBuilder addConsentPermission(ConsentPermission consentPermission) {
		this.permissions.add(consentPermission);
		return this;
	}

	public GetConsentRequestBuilder withCredentials(String iban) {
		this.requestInfo.setCredentials(new Credentials(iban));
		return this;
	}

	public GetConsentRequestBuilder withValidUntilDateTime(LocalDateTime validUntilDateTime) {
		this.validUntilDateTime = validUntilDateTime;
		return this;
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
