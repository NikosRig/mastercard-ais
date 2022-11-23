package com.nrigas.mastercard.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nrigas.mastercard.http.MastercardAisClient;
import com.nrigas.mastercard.model.AisConsentAccount;
import com.nrigas.mastercard.request.GetConsentRequest;
import com.nrigas.mastercard.response.GetConsentResponse;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import java.io.IOException;
import java.net.http.HttpResponse;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class Consent extends MastercardAisService {

    public Consent(MastercardAisClient client) {
        super(client);
    }

    public GetConsentResponse get(GetConsentRequest request) throws IOException, InterruptedException {

        GsonBuilder gsonBuilder = new GsonBuilder().setPrettyPrinting();

        JsonObjectBuilder requestInfoBuilder = Json.createObjectBuilder()
                .add("xRequestId", UUID.randomUUID().toString())
                .add("tppRedirectURI", request.tppRedirectURI)
                .add("aspspId", request.aspspId)
                .add("isLivePsuRequest", request.isLivePsuRequest);

        if (request.psuIPAddress != null) {
            requestInfoBuilder.add("psuIPAddress", request.psuIPAddress);
        }

        if (request.psuAgent != null) {
            requestInfoBuilder.add("psuAgent", request.psuAgent);
        }

        if (request.psuTppCustomerId != null) {
            requestInfoBuilder.add("psuTppCustomerId", request.psuTppCustomerId);
        }

        if (request.merchantId != null && request.merchantName != null) {
            JsonObject merchant = Json.createObjectBuilder()
                    .add("id", request.merchantId)
                    .add("merchantName", request.merchantName).
                    build();

            requestInfoBuilder.add("merchant", merchant);
        }

        if (request.iban != null) {
            JsonObjectBuilder credentials = Json.createObjectBuilder().add("iban", request.iban);
            requestInfoBuilder.add("credentials", credentials.build());
        }

        JsonArrayBuilder permissions = Json.createArrayBuilder();
        for (String permission : request.permissions) {
            permissions.add(permission);
        }
        JsonObjectBuilder payload = Json.createObjectBuilder()
            .add("requestInfo", requestInfoBuilder.build());

        payload.add("permissions", permissions.build());

        JsonArrayBuilder accounts = Json.createArrayBuilder();

        for (AisConsentAccount account : request.accounts) {

            JsonObject accountNumber = Json.createObjectBuilder()
                    .add("identification", account.getIban())
                    .add("schemeName", "IBAN")
                    .build();

            JsonObjectBuilder accountReference = Json.createObjectBuilder()
                    .add("currency", account.getCurrency())
                    .add("accountNumber", accountNumber);

            JsonObjectBuilder accountObj = Json.createObjectBuilder()
                    .add("accountReference", accountReference);

            accounts.add(accountObj);
        }

        payload.add("accounts", accounts);

        if (request.validUntilDateTime != null) {
            String validUntilDateTime = request.validUntilDateTime.format(DateTimeFormatter.ISO_DATE_TIME);
            payload.add("validUntilDateTime", validUntilDateTime);
        }

        HttpResponse<String> response = this.client.postJson(
                "/openbanking/connect/api/accounts/consents",
                payload.build().toString()
        );

        return new Gson().fromJson(response.body(), GetConsentResponse.class);
    }
}
