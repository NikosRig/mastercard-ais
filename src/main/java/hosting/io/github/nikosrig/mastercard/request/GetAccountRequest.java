package hosting.io.github.nikosrig.mastercard.request;

import hosting.io.github.nikosrig.mastercard.request.requestInfo.GetAccountRequestInfo;
import hosting.io.github.nikosrig.mastercard.requestBuilders.GetAccountRequestBuilder;

public class GetAccountRequest {

	public final String accountId;
	public final GetAccountRequestInfo requestInfo;

	public GetAccountRequest(GetAccountRequestBuilder builder) {
		this.accountId = builder.accountId;
		this.requestInfo = builder.requestInfo;
	}
}
