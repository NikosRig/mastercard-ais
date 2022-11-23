package com.nrigas.mastercard.http;

import java.io.IOException;
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

    public HttpResponse<String> postJson(String endpoint, String jsonBody) throws IOException, InterruptedException {

        URI url = URI.create(String.format("%s" + endpoint, this.host));
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(url)
                .header("Content-Type", "application/json")
                .header("Authorization", "")
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();

        return this.client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
    }
}
