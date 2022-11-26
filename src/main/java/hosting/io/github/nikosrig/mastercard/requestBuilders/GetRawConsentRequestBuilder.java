package hosting.io.github.nikosrig.mastercard.requestBuilders;

import hosting.io.github.nikosrig.mastercard.request.GetRawConsentRequest;
import hosting.io.github.nikosrig.mastercard.request.requestInfo.GetRawConsentRequestInfo;
import hosting.io.github.nikosrig.mastercard.request.requestInfo.Merchant;

public class GetRawConsentRequestBuilder {

	public GetRawConsentRequestInfo requestInfo;

	public GetRawConsentRequestBuilder() {
		this.requestInfo = new GetRawConsentRequestInfo();
	}

	public GetRawConsentRequestBuilder withConsentId(String consentId) {
		this.requestInfo.consentId = consentId;
		return this;
	}

	public GetRawConsentRequestBuilder withMerchant(String merchantId, String merchantName) {
		this.requestInfo.merchant = new Merchant(merchantId, merchantName);
		return this;
	}

	public GetRawConsentRequestBuilder withAspspId(String aspspId) {
		this.requestInfo.aspspId = aspspId;
		return this;
	}

	public GetRawConsentRequestBuilder withPsuAgent(String psuAgent) {
		this.requestInfo.psuAgent = psuAgent;
		return this;
	};

	public GetRawConsentRequestBuilder withPsuIPAddress(String psuIPAddress) {
		this.requestInfo.psuIPAddress = psuIPAddress;
		return this;
	};

	public GetRawConsentRequestBuilder withIsLivePsuRequest(Boolean isLivePsuRequest) {
		this.requestInfo.isLivePsuRequest = isLivePsuRequest;
		return this;
	};

	public GetRawConsentRequest build() {
		return new GetRawConsentRequest(this);
	}
}
