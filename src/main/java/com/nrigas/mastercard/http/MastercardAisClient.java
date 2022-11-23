package com.nrigas.mastercard.http;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class MastercardAisClient {

    private static final String productionHost = "https://api.mastercard.com";
    private static final String sandboxHost = "https://sandbox.api.mastercard.com";
    private final HttpClient client;
    private final String host;
    private final MastercardAisAuthUtil authUtil;

    public MastercardAisClient(HttpClient client, MastercardAisAuthUtil authUtil, Boolean sandboxMode) {
        this.client = client;
        this.authUtil = authUtil;
        this.host = sandboxMode ? sandboxHost : productionHost;
    }

    public HttpResponse postJson(String endpoint, String jsonBody) throws Exception {

        URI url = URI.create(String.format("%s" + endpoint, this.host));
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(url)
                .header("Content-Type", "application/json")
                .header("Authorization", this.authUtil.createAuthHeader(url, jsonBody))
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();

        HttpResponse response = this.client.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new Exception(response.body().toString());
        }

        return response;
    }
}
