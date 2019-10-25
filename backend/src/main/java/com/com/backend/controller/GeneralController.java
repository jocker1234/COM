package com.com.backend.controller;

import com.com.backend.validation.Validation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(value = {"http://localhost:4200", "http://54.37.234.192:4200"})
@RequestMapping("/api/auth")
public class GeneralController {

    @GetMapping("/countryList")
    public ResponseEntity<List<String>> listCountry(){
        List<String> countryList = Validation.getListCountries();
        return ResponseEntity.ok(countryList);
    }
}
