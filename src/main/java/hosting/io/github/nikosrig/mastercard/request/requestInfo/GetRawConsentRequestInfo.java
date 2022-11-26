package hosting.io.github.nikosrig.mastercard.request.requestInfo;

public class GetRawConsentRequestInfo extends RequestInfo {

	public GetRawConsentRequestInfo withConsentId(String consentId) {
		this.consentId = consentId;
		return this;
	}
}
