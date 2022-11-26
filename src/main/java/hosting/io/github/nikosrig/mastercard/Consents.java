package hosting.io.github.nikosrig.mastercard;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import hosting.io.github.nikosrig.mastercard.http.MastercardAisClient;
import hosting.io.github.nikosrig.mastercard.model.AuthorizedConsent;
import hosting.io.github.nikosrig.mastercard.model.Consent;
import hosting.io.github.nikosrig.mastercard.model.RawConsent;
import hosting.io.github.nikosrig.mastercard.request.AuthConsentRequest;
import hosting.io.github.nikosrig.mastercard.request.DeleteConsentRequest;
import hosting.io.github.nikosrig.mastercard.request.GetConsentRequest;
import hosting.io.github.nikosrig.mastercard.request.GetRawConsentRequest;
import org.json.JSONObject;

import java.net.http.HttpResponse;
import java.time.format.DateTimeFormatter;

public class Consents {

    private final Gson gson;
    private final MastercardAisClient client;

    public Consents(MastercardAisClient client) {
        this.client = client;
        this.gson = new GsonBuilder().create();
    }

    public AuthorizedConsent authorize(AuthConsentRequest request) throws Exception {

        JsonObject payload = new JsonObject();
        payload.add("requestInfo", this.gson.toJsonTree(request.requestInfo));
        payload.add("authorization", new JsonPrimitive(request.authorization));

        HttpResponse<String> response = this.client.postJson(
                "/openbanking/connect/api/accounts/consents/authorizations",
                payload.toString()
        );

        return this.gson.fromJson(response.body(), AuthorizedConsent.class);
    }

    public Consent get(GetConsentRequest request) throws Exception {

        JsonObject payload = new JsonObject();
        payload.add("requestInfo", this.gson.toJsonTree(request.requestInfo));
        payload.add("permissions", this.gson.toJsonTree(request.permissions));
        payload.add("accounts", this.gson.toJsonTree(request.accounts));

        if (request.validUntilDateTime != null) {
            String validUntilDateTime = request.validUntilDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'"));
            payload.add("validUntilDateTime", new JsonPrimitive(validUntilDateTime));
        }

        HttpResponse<String> response = this.client.postJson(
                "/openbanking/connect/api/accounts/consents",
                payload.toString()
        );
        JSONObject responsePayload = new JSONObject(response.body());

        return new Consent(
                responsePayload.getString("consentRequestId"),
                responsePayload.getJSONObject("_links").getString("scaRedirect"),
                responsePayload.getJSONObject("originalRequestInfo").getString("xRequestId")
        );
    }

    public void delete(DeleteConsentRequest request) throws Exception {
        this.client.postJson(
                "/openbanking/connect/api/accounts/consents/delete",
                this.gson.toJson(request)
        );
    }

    public RawConsent getRaw(GetRawConsentRequest request) throws Exception {
        HttpResponse<String> response = this.client.postJson(
                "/openbanking/connect/api/accounts/consents/raw",
                this.gson.toJson(request)
        );

        return this.gson.fromJson(response.body(), RawConsent.class);
    }
}
