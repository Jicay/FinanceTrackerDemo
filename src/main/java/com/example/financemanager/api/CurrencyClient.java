package com.example.financemanager.api;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CurrencyClient {
    private static final Logger log = LoggerFactory.getLogger(CurrencyClient.class);
    private static final String URL = "https://cdn.taux.live/api/ecb.json";

    public static float getDollarRate() {
        try (HttpClient client = HttpClient.newHttpClient()) {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(URL))
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            CurrencyResponse currencyResponse = mapper.readValue(response.body(), CurrencyResponse.class);
            return 1.0f / currencyResponse.rates().get("EUR");
        } catch (IOException | InterruptedException e) {
            log.error("Error while getting currency", e);
        }
        return 1.0f;
    }
}
