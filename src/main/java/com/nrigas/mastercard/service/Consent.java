package com.nrigas.mastercard.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nrigas.mastercard.http.MastercardAisClient;
import com.nrigas.mastercard.model.ConsentAccount;
import com.nrigas.mastercard.model.ConsentPermission;
import com.nrigas.mastercard.model.Psu;
import com.nrigas.mastercard.request.GetConsentRequest;
import com.nrigas.mastercard.response.GetConsentResponse;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import java.io.IOException;
import java.net.http.HttpResponse;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
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
                .add("aspspId", request.aspspId);

        JsonObjectBuilder payload = Json.createObjectBuilder();

        this.addPsu(request.psu, requestInfoBuilder);
        this.addMerchant(request, requestInfoBuilder);
        this.addAccounts(request.consentAccountsList, payload);
        this.addPermissions(request.permissions, payload);

        if (request.credentials != null) {
            JsonObjectBuilder credentials = Json.createObjectBuilder().add("iban", request.credentials.getIban());
            requestInfoBuilder.add("credentials", credentials);
        }
        payload.add("requestInfo", requestInfoBuilder.build());

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

    private void addPermissions(List<ConsentPermission> permissionsList, JsonObjectBuilder payload) {

        JsonArrayBuilder permissions = Json.createArrayBuilder();
        permissionsList.forEach( permission -> {
            permissions.add(permission.toString());
        });
        payload.add("permissions", permissions);
    }

    private void addAccounts(ArrayList<ConsentAccount> consentAccountList, JsonObjectBuilder payload) {

        JsonArrayBuilder accounts = Json.createArrayBuilder();

        consentAccountList.forEach( account -> {
            JsonObjectBuilder accountNumber = Json.createObjectBuilder()
                    .add("identification", account.getIban())
                    .add("schemeName", "IBAN");

            JsonObjectBuilder accountReference = Json.createObjectBuilder()
                    .add("currency", account.getCurrency())
                    .add("accountNumber", accountNumber);

            JsonObjectBuilder accountObj = Json.createObjectBuilder()
                    .add("accountReference", accountReference);

            accounts.add(accountObj);
        });
        payload.add("accounts", accounts);
    }

    private void addPsu(Psu psu, JsonObjectBuilder payload) {

        payload.add("isLivePsuRequest", psu.isLivePsuRequest());

        if (psu.getIpAddress() != null) {
            payload.add("psuIPAddress", psu.getIpAddress());
        }

        if (psu.getAgent() != null) {
            payload.add("psuAgent", psu.getAgent());
        }

        if (psu.getTppCustomerId() != null) {
            payload.add("psuTppCustomerId", psu.getTppCustomerId());
        }
    }

    private void addMerchant(GetConsentRequest request, JsonObjectBuilder requestInfoBuilder) {

        if (request.merchant == null) {
            return;
        }

        JsonObject merchant = Json.createObjectBuilder()
                .add("id", request.merchant.getId())
                .add("merchantName", request.merchant.getName())
                .build();

        requestInfoBuilder.add("merchant", merchant);
    }
}
