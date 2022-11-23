package com.nrigas.mastercard.service;

import com.nrigas.mastercard.http.MastercardAisClient;
import com.nrigas.mastercard.model.Merchant;
import com.nrigas.mastercard.model.Psu;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

abstract public class MastercardAisService {

    protected final MastercardAisClient client;

    public MastercardAisService(MastercardAisClient client) {
        this.client = client;
    }

    public void addPsu(Psu psu, JsonObjectBuilder payload) {

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
