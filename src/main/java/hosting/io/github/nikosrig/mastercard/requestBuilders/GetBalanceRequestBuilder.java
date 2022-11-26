package hosting.io.github.nikosrig.mastercard.requestBuilders;

import hosting.io.github.nikosrig.mastercard.request.GetBalanceRequest;
import hosting.io.github.nikosrig.mastercard.request.requestInfo.Merchant;
import hosting.io.github.nikosrig.mastercard.request.requestInfo.RequestInfo;

public class GetBalanceRequestBuilder {

	public final RequestInfo requestInfo;
	public String accountId;

	public GetBalanceRequestBuilder() {
		this.requestInfo = new RequestInfo();
	}

	public GetBalanceRequestBuilder withConsentId(String consentId) {
		this.requestInfo.consentId = consentId;
		return this;
	}

	public GetBalanceRequestBuilder withMerchant(String merchantId, String merchantName) {
		this.requestInfo.merchant = new Merchant(merchantId, merchantName);
		return this;
	}

	public GetBalanceRequestBuilder withAspspId(String aspspId) {
		this.requestInfo.aspspId = aspspId;
		return this;
	}

	public GetBalanceRequestBuilder withPsuAgent(String psuAgent) {
		this.requestInfo.psuAgent = psuAgent;
		return this;
	};

	public GetBalanceRequestBuilder withPsuIPAddress(String psuIPAddress) {
		this.requestInfo.psuIPAddress = psuIPAddress;
		return this;
	};

	public GetBalanceRequestBuilder withIsLivePsuRequest(Boolean isLivePsuRequest) {
		this.requestInfo.isLivePsuRequest = isLivePsuRequest;
		return this;
	};

	public GetBalanceRequestBuilder withPsuTppCustomerId(String psuTppCustomerId) {
		this.requestInfo.psuTppCustomerId = psuTppCustomerId;
		return this;
	};

	public GetBalanceRequestBuilder withAccountId(String accountId) {
		this.accountId = accountId;
		return this;
	};

	public GetBalanceRequest build() {
		return new GetBalanceRequest(this);
	}
}
