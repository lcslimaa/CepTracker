package com.lcslimaa.CepTracker.provider;

import com.lcslimaa.CepTracker.dto.AddressResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class CepProviderImpl implements CepProvider {

    private final WebClient webClient;
    private final String baseUrl;

    public CepProviderImpl(WebClient webClient,
                           @Value("${external.cep.base-url}") String baseUrl) {
        this.webClient = webClient;
        this.baseUrl = baseUrl;
    }

    @Override
    public AddressResponse getAddress(String cep) {
        try{
            return webClient.get()
                    .uri(baseUrl + "/cep/{cep}", cep)
                    .retrieve()
                    .bodyToMono(AddressResponse.class)
                    .block();
        } catch (Exception e) {
            throw new RuntimeException("Error calling external CEP API", e);
        }
    }
}
