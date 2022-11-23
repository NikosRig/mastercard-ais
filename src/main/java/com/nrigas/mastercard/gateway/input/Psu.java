package com.nrigas.mastercard.gateway.input;

public record Psu(
    Boolean isLivePsuRequest,
    String psuIPAddress,
    String psuAgent,
    String psuTppCustomerId
) {
}
