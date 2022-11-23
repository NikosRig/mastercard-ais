package com.nrigas.mastercard.requestBuilders;

import com.nrigas.mastercard.model.ConsentAccount;
import com.nrigas.mastercard.model.ConsentPermission;
import com.nrigas.mastercard.model.Credentials;
import com.nrigas.mastercard.service.Consent.request.ConsentRequest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

public class ConsentRequestBuilder extends RequestBuilder {

	private final ArrayList<ConsentPermission> consentPermissions;
	private final ArrayList<ConsentAccount> consentAccounts;
	private String tppRedirectUri;
	private Credentials credentials = null;
	private LocalDateTime validUntilDateTime = null;

	public ConsentRequestBuilder() {
		this.consentPermissions = new ArrayList<ConsentPermission>();
		this.consentAccounts = new ArrayList<ConsentAccount>();
	}

	@Override
	public ConsentRequestBuilder withPsu(
			Boolean isLivePsuRequest,
			String psuAgent,
			String psuIpAddress,
			String psuTppCustomerId
	) {
		super.withPsu(isLivePsuRequest, psuAgent, psuIpAddress, psuTppCustomerId);
		return this;
	}

	@Override
	public ConsentRequestBuilder withMerchant(String merchantId, String merchantName) {
		super.withMerchant(merchantId, merchantName);
		return this;
	}

	@Override
	public ConsentRequestBuilder withAspsId(String aspsId) {
		super.withAspsId(aspsId);
		return this;
	}

	public ConsentRequestBuilder withTppRedirectUri(String tppRedirectUri) {
		this.tppRedirectUri = tppRedirectUri;
		return this;
	}

	public ConsentRequestBuilder addConsentPermission(ConsentPermission consentPermission) {
		this.consentPermissions.add(consentPermission);
		return this;
	}

	public ConsentRequestBuilder withCredentials(String iban) {
		this.credentials = new Credentials(iban);
		return this;
	}

	public ConsentRequestBuilder withValidUntilDateTime(LocalDateTime validUntilDateTime) {
		this.validUntilDateTime = validUntilDateTime;
		return this;
	}

	public ConsentRequestBuilder addConsentAccount(String id, String currency) {
		ConsentAccount consentAccount = new ConsentAccount(id, currency);
		this.consentAccounts.add(consentAccount);
		return this;
	}

	public ConsentRequest build() {

		return new ConsentRequest(
				this.aspsId,
				this.tppRedirectUri,
				this.consentPermissions,
				this.consentAccounts,
				this.psu,
				Optional.ofNullable(this.merchant),
				Optional.ofNullable(this.credentials),
				Optional.ofNullable(this.validUntilDateTime)
		);
	}
}
