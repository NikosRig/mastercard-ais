package com.nrigas.mastercard.service;

import com.nrigas.mastercard.http.MastercardAisClient;

abstract public class MastercardAisService {

    protected final MastercardAisClient client;

    public MastercardAisService(MastercardAisClient client) {
        this.client = client;
    }
}
