package com.lcslimaa.CepTracker.controller;

import com.lcslimaa.CepTracker.dto.AddressResponse;
import com.lcslimaa.CepTracker.dto.LogResponse;
import com.lcslimaa.CepTracker.entity.LogEntity;
import com.lcslimaa.CepTracker.service.CepService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cep")
public class CepController {

    private final CepService cepService;

    public CepController(CepService cepService) {
        this.cepService = cepService;
    }

    @GetMapping("/{cep}")
    public ResponseEntity<AddressResponse> findAddressByCep(@PathVariable String cep) {

        AddressResponse response = cepService.findAddressByCep(cep);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/logs")
    public List<LogResponse> getLogs()  {
        return cepService.getAllLogs();
    }

}
