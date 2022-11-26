package hosting.io.github.nikosrig.mastercard.requestBuilders;

import hosting.io.github.nikosrig.mastercard.request.ListAccountsRequest;
import hosting.io.github.nikosrig.mastercard.request.requestInfo.GetRawConsentRequestInfo;
import hosting.io.github.nikosrig.mastercard.request.requestInfo.Merchant;
import hosting.io.github.nikosrig.mastercard.request.requestInfo.RequestInfo;

public class ListAccountsRequestBuilder {

	public RequestInfo requestInfo;

	public ListAccountsRequestBuilder() {
		this.requestInfo = new GetRawConsentRequestInfo();
	}

	public ListAccountsRequestBuilder withConsentId(String consentId) {
		this.requestInfo.consentId = consentId;
		return this;
	}

	public ListAccountsRequestBuilder withMerchant(String merchantId, String merchantName) {
		this.requestInfo.merchant = new Merchant(merchantId, merchantName);
		return this;
	}

	public ListAccountsRequestBuilder withAspspId(String aspspId) {
		this.requestInfo.aspspId = aspspId;
		return this;
	}

	public ListAccountsRequestBuilder withPsuAgent(String psuAgent) {
		this.requestInfo.psuAgent = psuAgent;
		return this;
	};

	public ListAccountsRequestBuilder withPsuIPAddress(String psuIPAddress) {
		this.requestInfo.psuIPAddress = psuIPAddress;
		return this;
	};

	public ListAccountsRequestBuilder withIsLivePsuRequest(Boolean isLivePsuRequest) {
		this.requestInfo.isLivePsuRequest = isLivePsuRequest;
		return this;
	};

	public ListAccountsRequestBuilder withPsuTppCustomerId(String psuTppCustomerId) {
		this.requestInfo.psuTppCustomerId = psuTppCustomerId;
		return this;
	}

	public ListAccountsRequest build() {
		return new ListAccountsRequest(this);
	}
}
