package com.nrigas.mastercard.requestBuilders;

import com.nrigas.mastercard.model.ConsentAccount;
import com.nrigas.mastercard.model.ConsentPermission;
import com.nrigas.mastercard.model.Credentials;
import com.nrigas.mastercard.model.Merchant;
import com.nrigas.mastercard.service.Consent.GetConsentRequest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

public class GetConsentRequestBuilder extends RequestBuilder {

	private final ArrayList<ConsentPermission> consentPermissions;
	private final ArrayList<ConsentAccount> consentAccounts;
	private Merchant merchant = null;
	private String tppRedirectUri;
	private Credentials credentials = null;
	private LocalDateTime validUntilDateTime = null;

	public GetConsentRequestBuilder() {
		this.consentPermissions = new ArrayList<ConsentPermission>();
		this.consentAccounts = new ArrayList<ConsentAccount>();
	}

	@Override
	public GetConsentRequestBuilder withPsu(
			Boolean isLivePsuRequest,
			String psuAgent,
			String psuIpAddress,
			String psuTppCustomerId
	) {
		super.withPsu(isLivePsuRequest, psuAgent, psuIpAddress, psuTppCustomerId);
		return this;
	}

	public GetConsentRequestBuilder withMerchant(String merchantId, String merchantName) {
		this.merchant = new Merchant(merchantId, merchantName);
		return this;
	}

	@Override
	public GetConsentRequestBuilder withAspsId(String aspsId) {
		super.withAspsId(aspsId);
		return this;
	}

	public GetConsentRequestBuilder withTppRedirectUri(String tppRedirectUri) {
		this.tppRedirectUri = tppRedirectUri;
		return this;
	}

	public GetConsentRequestBuilder addConsentPermission(ConsentPermission consentPermission) {
		this.consentPermissions.add(consentPermission);
		return this;
	}

	public GetConsentRequestBuilder withCredentials(String iban) {
		this.credentials = new Credentials(iban);
		return this;
	}

	public GetConsentRequestBuilder withValidUntilDateTime(LocalDateTime validUntilDateTime) {
		this.validUntilDateTime = validUntilDateTime;
		return this;
	}

	public GetConsentRequestBuilder addConsentAccount(String id, String currency) {
		ConsentAccount consentAccount = new ConsentAccount(id, currency);
		this.consentAccounts.add(consentAccount);
		return this;
	}

	public GetConsentRequest build() {

		return new GetConsentRequest(
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
