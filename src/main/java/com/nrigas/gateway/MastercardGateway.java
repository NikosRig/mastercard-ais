package com.nrigas.gateway;

import com.nrigas.gateway.exceptions.GatewayException;
import com.nrigas.gateway.input.ConsentPermission;
import com.nrigas.gateway.input.RequestInfo;
import com.nrigas.gateway.output.AccountInformationConsent;
import org.json.JSONObject;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

final public class MastercardGateway {

    private final MastercardAuthUtil authUtil;
    private final HttpClient client;
    private final MastercardGatewayConfig config;
    private final String host;

    public MastercardGateway(
        final MastercardAuthUtil authUtil,
        final HttpClient client,
        final MastercardGatewayConfig config
    ) {
        this.authUtil = authUtil;
        this.client = client;
        this.config = config;
        this.host = config.isSandbox() ? "https://sandbox.api.mastercard.com" : "https://api.mastercard.com";
    }

    public AccountInformationConsent getAccountInformationConsent(
            final RequestInfo requestInfo,
            final List<ConsentPermission> permissions
    ) throws GatewayException {

        JsonObject payload = Json.createObjectBuilder()
                .add("requestInfo", this.buildRequestInfoJson(requestInfo))
                .add("permissions", this.buildPermissionsJson(permissions))
                .build();

        try {
            URI url = URI.create(String.format("%s/openbanking/connect/api/accounts/consents", this.host));
            String authHeader = this.authUtil.createAuthHeader(url, payload.toString());

            HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(url)
                .header("Content-Type", "application/json")
                .header("Authorization", authHeader)
                .POST(HttpRequest.BodyPublishers.ofString(payload.toString()))
                .build();

            HttpResponse<String> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            JSONObject responsePayload = new JSONObject(response.body());

            if (response.statusCode() != 200 || !Objects.equals(responsePayload.getString("aspspSCAApproach"), "REDIRECT")) {
                throw new Exception(response.toString());
            }

           return new AccountInformationConsent(
                   responsePayload.getJSONObject("originalRequestInfo").getString("xRequestId"),
                   responsePayload.getString("consentRequestId"),
                   responsePayload.getJSONObject("_links").getString("scaRedirect")
           );
        } catch (Exception e) {
            throw new GatewayException(e.getMessage());
        }
    }

    private JsonArray buildPermissionsJson(final List<ConsentPermission> permissions) {
        JsonArrayBuilder permissionsArray = Json.createArrayBuilder();

        for (ConsentPermission permission : permissions) {
            permissionsArray.add(permission.toString());
        }

        return permissionsArray.build();
    }

    private JsonObject buildRequestInfoJson(RequestInfo requestInfo) {
        return Json.createObjectBuilder()
                .add("xRequestId", UUID.randomUUID().toString())
                .add("tppRedirectURI", this.config.tppRedirectUrl())
                .add("aspspId", requestInfo.aspspId())
                .build();
    }
}
