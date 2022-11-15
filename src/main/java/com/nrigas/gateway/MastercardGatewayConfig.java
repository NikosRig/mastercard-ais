package com.nrigas.gateway;

public record MastercardGatewayConfig(
    String tppRedirectUrl,
    Boolean isSandbox
) {
}
