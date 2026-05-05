package com.lcslimaa.CepTracker.provider;

import com.lcslimaa.CepTracker.dto.AddressResponse;

public interface CepProvider {
    AddressResponse getAddress(String cep);
}
