package com.lcslimaa.CepTracker.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lcslimaa.CepTracker.dto.AddressResponse;
import com.lcslimaa.CepTracker.dto.LogResponse;
import com.lcslimaa.CepTracker.entity.LogEntity;
import com.lcslimaa.CepTracker.provider.CepProvider;
import com.lcslimaa.CepTracker.repository.LogRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CepService {

    private final CepProvider cepProvider;
    private final LogRepository logRepository;
    private final ObjectMapper objectMapper;

    public CepService(CepProvider cepProvider, LogRepository logRepository, ObjectMapper objectMapper) {
        this.cepProvider = cepProvider;
        this.logRepository = logRepository;
        this.objectMapper = objectMapper;
    }

    public AddressResponse findAddressByCep(String cep) {
        validateCep(cep);

        AddressResponse response = cepProvider.getAddress(cep);

        saveLog(cep, response);

        return response;
    }

    public List<LogResponse> getAllLogs() {
        return logRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    private void validateCep(String cep){
        if(cep == null || cep.isEmpty() || !cep.matches("^\\d{8}$")){
            throw new IllegalArgumentException("Cep must have 8 digits");
        }
    }

    private void saveLog(String cep, AddressResponse response) {
        String json = serializeResponse(response);

        LogEntity log = new LogEntity();
        log.setCep(cep);
        log.setResponseJson(json);
        log.setCreatedAt(LocalDateTime.now());

        logRepository.save(log);
    }

    private LogResponse toResponse(LogEntity log) {
        LogResponse response = new LogResponse();
        response.setCep(log.getCep());
        response.setResponseJson(log.getResponseJson());
        response.setCreatedAt(log.getCreatedAt());
        return response;
    }

    private String serializeResponse(AddressResponse response) {
        try {
            return objectMapper.writeValueAsString(response);
        } catch (Exception e) {
            System.err.println("Failed to serialize response: " + e.getMessage());
            return "{}";
        }
    }
}
