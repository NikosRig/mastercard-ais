package hosting.io.github.nikosrig.mastercard.model;

final public class Consent {

    public final String consentRequestId;
    public final String scaRedirectUri;
    public final String xRequestId;

    public Consent(
            String consentRequestId,
            String scaRedirectUri,
            String xRequestId
    ) {
        this.consentRequestId = consentRequestId;
        this.scaRedirectUri = scaRedirectUri;
        this.xRequestId = xRequestId;
    }
}
