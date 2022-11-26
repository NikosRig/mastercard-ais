package hosting.io.github.nikosrig.mastercard.request;

import hosting.io.github.nikosrig.mastercard.request.requestInfo.RequestInfo;
import hosting.io.github.nikosrig.mastercard.requestBuilders.GetTransactionRequestBuilder;

public class GetTransactionRequest {

	public final RequestInfo requestInfo;
	public final String accountId;
	public final String transactionId;

	public GetTransactionRequest(GetTransactionRequestBuilder builder) {
		this.requestInfo = builder.requestInfo;
		this.accountId = builder.accountId;
		this.transactionId = builder.transactionId;
	}
}
