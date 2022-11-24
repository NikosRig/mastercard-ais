package com.nrigas.mastercard.service.Consent;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonPrimitive;
import com.nrigas.mastercard.http.MastercardAisClient;
import com.nrigas.mastercard.model.Consent;
import com.nrigas.mastercard.request.AuthConsentRequest;
import com.nrigas.mastercard.request.DeleteConsentRequest;
import com.nrigas.mastercard.request.GetConsentRequest;
import com.nrigas.mastercard.service.Consent.response.AuthorizeConsentResponse;
import com.nrigas.mastercard.service.MastercardAisService;
import org.json.JSONObject;

import java.net.http.HttpResponse;
import java.time.format.DateTimeFormatter;

public class Consents extends MastercardAisService {

    private final Gson gson;

    public Consents(MastercardAisClient client) {
        super(client);
        this.gson = new GsonBuilder().create();
    }

    public AuthorizeConsentResponse authorize(AuthConsentRequest request) throws Exception {

        com.google.gson.JsonObject payload = new com.google.gson.JsonObject();
        payload.add("requestInfo", this.gson.toJsonTree(request.requestInfo));
        payload.add("authorization", new JsonPrimitive(request.authorization));

        HttpResponse<String> response = this.client.postJson(
                "/openbanking/connect/api/accounts/consents/authorizations",
                payload.toString()
        );

        return this.gson.fromJson(response.body(), AuthorizeConsentResponse.class);
    }

    public Consent get(GetConsentRequest request) throws Exception {

        com.google.gson.JsonObject payload = new com.google.gson.JsonObject();
        payload.add("requestInfo", this.gson.toJsonTree(request.requestInfo));
        payload.add("permissions", this.gson.toJsonTree(request.permissions));
        payload.add("accounts", this.gson.toJsonTree(request.accounts));

        if (request.validUntilDateTime != null) {
            String validUntilDateTime = request.validUntilDateTime.format(DateTimeFormatter.ISO_DATE_TIME);
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
}
