package com.nrigas.mastercard.requestBuilders;

import com.nrigas.mastercard.request.GetTransactionRequest;
import com.nrigas.mastercard.request.requestInfo.Merchant;
import com.nrigas.mastercard.request.requestInfo.TransactionRequestInfo;

public class GetTransactionRequestBuilder {

	public String consentId;
	public TransactionRequestInfo requestInfo;
	public String accountId;
	public String transactionId;

	public GetTransactionRequestBuilder() {
		this.requestInfo = new TransactionRequestInfo();
	}

	public GetTransactionRequestBuilder withConsentId(String consentId) {
		this.requestInfo.consentId = consentId;
		return this;
	}

	public GetTransactionRequestBuilder withMerchant(String merchantId, String merchantName) {
		this.requestInfo.merchant = new Merchant(merchantId, merchantName);
		return this;
	}

	public GetTransactionRequestBuilder withAspspId(String aspspId) {
		this.requestInfo.aspspId = aspspId;
		return this;
	}

	public GetTransactionRequestBuilder withPsuAgent(String psuAgent) {
		this.requestInfo.psuAgent = psuAgent;
		return this;
	};

	public GetTransactionRequestBuilder withPsuIPAddress(String psuIPAddress) {
		this.requestInfo.psuIPAddress = psuIPAddress;
		return this;
	};

	public GetTransactionRequestBuilder withIsLivePsuRequest(Boolean isLivePsuRequest) {
		this.requestInfo.isLivePsuRequest = isLivePsuRequest;
		return this;
	};

	public GetTransactionRequestBuilder withPsuTppCustomerId(String psuTppCustomerId) {
		this.requestInfo.psuTppCustomerId = psuTppCustomerId;
		return this;
	};

	public GetTransactionRequestBuilder withAccountId(String accountId) {
		this.accountId = accountId;
		return this;
	};

	public GetTransactionRequestBuilder withTransactionId(String transactionId) {
		this.transactionId = transactionId;
		return this;
	};

	public GetTransactionRequest build() {
		return new GetTransactionRequest(this);
	}
}
