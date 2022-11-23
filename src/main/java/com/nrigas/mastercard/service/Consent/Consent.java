package com.nrigas.mastercard.service.Consent;

import com.nrigas.mastercard.http.MastercardAisClient;
import com.nrigas.mastercard.model.ConsentAccount;
import com.nrigas.mastercard.model.ConsentPermission;
import com.nrigas.mastercard.service.Consent.request.AuthConsentRequest;
import com.nrigas.mastercard.service.Consent.request.DeleteConsentRequest;
import com.nrigas.mastercard.service.Consent.request.GetConsentRequest;
import com.nrigas.mastercard.service.Consent.response.AuthorizeConsentResponse;
import com.nrigas.mastercard.service.Consent.response.GetConsentResponse;
import com.nrigas.mastercard.service.MastercardAisService;
import org.json.JSONObject;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import java.net.http.HttpResponse;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Consent extends MastercardAisService {

    public Consent(MastercardAisClient client) {
        super(client);
    }

    public AuthorizeConsentResponse authorize(AuthConsentRequest request) throws Exception {

        JsonObjectBuilder requestInfoBuilder = Json.createObjectBuilder()
                .add("xRequestId", UUID.randomUUID().toString())
                .add("aspspId", request.aspspId);

        this.addPsu(request.psu, requestInfoBuilder);
        this.addMerchant(request.merchant, requestInfoBuilder);

        JsonObjectBuilder payload = Json.createObjectBuilder()
                .add("requestInfo", requestInfoBuilder)
                .add("authorization", request.authCode);

        HttpResponse<String> response = this.client.postJson(
                "/openbanking/connect/api/accounts/consents/authorizations",
                payload.build().toString()
        );
        JSONObject responsePayload = new JSONObject(response.body());

        return new AuthorizeConsentResponse(
                responsePayload.getString("consentId"),
                responsePayload.getString("consentRequestId"),
                responsePayload.getJSONObject("originalRequestInfo").getString("xRequestId")
        );
    }

    public GetConsentResponse get(GetConsentRequest request) throws Exception {

        JsonObjectBuilder requestInfoBuilder = Json.createObjectBuilder()
                .add("xRequestId", UUID.randomUUID().toString())
                .add("tppRedirectURI", request.tppRedirectURI)
                .add("aspspId", request.aspspId);

        JsonObjectBuilder payload = Json.createObjectBuilder();

        this.addPsu(request.psu, requestInfoBuilder);
        this.addMerchant(request.merchant, requestInfoBuilder);
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
        JSONObject responsePayload = new JSONObject(response.body());

        return new GetConsentResponse(
                responsePayload.getString("consentRequestId"),
                responsePayload.getJSONObject("_links").getString("scaRedirect"),
                responsePayload.getJSONObject("originalRequestInfo").getString("xRequestId")
        );
    }

    public void delete(DeleteConsentRequest request) throws Exception {

        JsonObjectBuilder requestInfoBuilder = Json.createObjectBuilder()
                .add("xRequestId", UUID.randomUUID().toString())
                .add("aspspId", request.aspspId)
                .add("psuTppCustomerId", request.psuTppCustomerId);
        this.addMerchant(request.merchant, requestInfoBuilder);

        JsonObject payload = Json.createObjectBuilder()
                .add("consentId", request.consentId)
                .add("requestInfo", requestInfoBuilder)
                .build();

        HttpResponse<String> response = this.client.postJson(
                "/openbanking/connect/api/accounts/consents/delete",
                payload.toString()
        );
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
                    .add("identification", account.getId())
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
}
