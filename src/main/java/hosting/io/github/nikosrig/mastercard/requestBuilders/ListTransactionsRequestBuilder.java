package hosting.io.github.nikosrig.mastercard.requestBuilders;

import hosting.io.github.nikosrig.mastercard.request.ListTransactionsRequest;
import hosting.io.github.nikosrig.mastercard.request.requestInfo.Merchant;
import hosting.io.github.nikosrig.mastercard.request.requestInfo.RequestInfo;

public class ListTransactionsRequestBuilder {

	public final RequestInfo requestInfo;
	public String accountId;
	public Integer limit;
	public String bookingDateTo;
	public String bookingDateFrom;
	public String offset;

	public ListTransactionsRequestBuilder() {
		this.requestInfo = new RequestInfo();
	}

	public ListTransactionsRequestBuilder withBookingTo(String bookingDateTo) {
		this.bookingDateTo = bookingDateTo;
		return this;
	}

	public ListTransactionsRequestBuilder withBookingDateFrom(String bookingDateFrom) {
		this.bookingDateFrom = bookingDateFrom;
		return this;
	}

	public ListTransactionsRequestBuilder withOffset(String offset) {
		this.offset = offset;
		return this;
	}

	public ListTransactionsRequestBuilder withLimit(int limit) {
		this.limit = limit;
		return this;
	}

	public ListTransactionsRequestBuilder withConsentId(String consentId) {
		this.requestInfo.consentId = consentId;
		return this;
	}

	public ListTransactionsRequestBuilder withMerchant(String merchantId, String merchantName) {
		this.requestInfo.merchant = new Merchant(merchantId, merchantName);
		return this;
	}

	public ListTransactionsRequestBuilder withAspspId(String aspspId) {
		this.requestInfo.aspspId = aspspId;
		return this;
	}

	public ListTransactionsRequestBuilder withPsuAgent(String psuAgent) {
		this.requestInfo.psuAgent = psuAgent;
		return this;
	};

	public ListTransactionsRequestBuilder withPsuIPAddress(String psuIPAddress) {
		this.requestInfo.psuIPAddress = psuIPAddress;
		return this;
	};

	public ListTransactionsRequestBuilder withIsLivePsuRequest(Boolean isLivePsuRequest) {
		this.requestInfo.isLivePsuRequest = isLivePsuRequest;
		return this;
	};

	public ListTransactionsRequestBuilder withPsuTppCustomerId(String psuTppCustomerId) {
		this.requestInfo.psuTppCustomerId = psuTppCustomerId;
		return this;
	};

	public ListTransactionsRequestBuilder withAccountId(String accountId) {
		this.accountId = accountId;
		return this;
	};

	public ListTransactionsRequest build() {
		return new ListTransactionsRequest(this);
	}
}
