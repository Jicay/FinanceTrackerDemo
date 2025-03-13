package com.example.financemanager.api;

import java.util.Map;

public record CurrencyResponse(
        String base,
        Map<String, Float> rates
) {
}
