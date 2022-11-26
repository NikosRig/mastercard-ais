package hosting.io.github.nikosrig.mastercard.requestBuilders;

import hosting.io.github.nikosrig.mastercard.request.DeleteConsentRequest;
import hosting.io.github.nikosrig.mastercard.request.requestInfo.Merchant;
import hosting.io.github.nikosrig.mastercard.request.requestInfo.RequestInfo;

public class DeleteConsentRequestBuilder {

	public RequestInfo requestInfo;
	public String consentId;

	public DeleteConsentRequestBuilder() {
		this.requestInfo = new RequestInfo();
	}

	public DeleteConsentRequestBuilder withMerchant(String merchantId, String merchantName) {
		this.requestInfo.merchant = new Merchant(merchantId, merchantName);
		return this;
	}

	public DeleteConsentRequestBuilder withAspspId(String aspspId) {
		this.requestInfo.aspspId = aspspId;
		return this;
	}

	public DeleteConsentRequestBuilder withPsuTppCustomerId(String psuTppCustomerId) {
		this.requestInfo.psuTppCustomerId = psuTppCustomerId;
		return this;
	};

	public DeleteConsentRequestBuilder withConsentId(String consentId) {
		this.consentId = consentId;
		return this;
	}

	public DeleteConsentRequest build() {
		return new DeleteConsentRequest(this);
	}
}
