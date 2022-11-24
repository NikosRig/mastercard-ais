package com.nrigas.mastercard;

import com.nrigas.mastercard.http.MastercardAisClient;
import com.nrigas.mastercard.request.requestInfo.Merchant;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

abstract public class MastercardAisService {

    protected final MastercardAisClient client;

    public MastercardAisService(MastercardAisClient client) {
        this.client = client;
    }

    public void addMerchant(Merchant merchant, JsonObjectBuilder requestInfoBuilder) {

        if (merchant == null) {
            return;
        }

        JsonObject merchantObj = Json.createObjectBuilder()
                .add("id", merchant.getId())
                .add("name", merchant.getName())
                .build();

        requestInfoBuilder.add("merchant", merchantObj);
    }
}
