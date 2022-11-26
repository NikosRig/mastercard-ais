package hosting.io.github.nikosrig.mastercard.request;

import hosting.io.github.nikosrig.mastercard.request.requestInfo.RequestInfo;
import hosting.io.github.nikosrig.mastercard.requestBuilders.GetBalanceRequestBuilder;

public class GetBalanceRequest {

	public final String accountId;
	public RequestInfo requestInfo;

	public GetBalanceRequest(GetBalanceRequestBuilder builder) {
		this.requestInfo = builder.requestInfo;
		this.accountId = builder.accountId;
	}
}
