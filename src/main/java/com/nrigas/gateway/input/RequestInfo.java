package com.nrigas.gateway.input;

import org.jetbrains.annotations.Nullable;

public record RequestInfo(
    String aspspId,
    @Nullable Psu psu,
    @Nullable Merchant merchant
) {
}
