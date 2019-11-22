package com.com.backend.controller;

import com.com.backend.dto.request.DictionaryDtoRequest;
import com.com.backend.dto.response.DictionaryDtoResponse;
import com.com.backend.exception.AccessException;
import com.com.backend.exception.AppException;
import com.com.backend.model.Dictionary;
import com.com.backend.service.DictionaryService;
import org.apache.tomcat.jni.Directory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(value = {"http://localhost:4200", "http://54.37.234.192:4200"})
@RequestMapping("/api/dictionary")
public class DictionaryController {

    private DictionaryService directoryService;

    public DictionaryController(DictionaryService directoryService) {
        this.directoryService = directoryService;
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<DictionaryDtoResponse>> getAll(@RequestHeader(value = "Authorization")String token)
                                                                                                throws AccessException {
        List<DictionaryDtoResponse> directoryDtoResponses = directoryService.getAll(token);
        return ResponseEntity.ok(directoryDtoResponses);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<DictionaryDtoResponse> getOne(@RequestHeader(value = "Authorization")String token,
                                                                            @PathVariable Long id) throws AppException {
        DictionaryDtoResponse directoryDtoResponse = directoryService.getOne(id, token);
        return ResponseEntity.ok(directoryDtoResponse);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<DictionaryDtoResponse> updateParameter(@RequestHeader(value = "Authorization")String token,
                    @PathVariable Long id, @RequestBody DictionaryDtoRequest directoryDtoRequest) throws AccessException {
        DictionaryDtoResponse directoryDtoResponse = directoryService.updataParameter(token, id, directoryDtoRequest);
        return ResponseEntity.ok(directoryDtoResponse);
    }

    @PutMapping(value = "/{id}/image")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<DictionaryDtoResponse> updateParameter(@RequestHeader(value = "Authorization")String token,
                    @PathVariable Long id, @RequestParam MultipartFile file) throws IOException {
        DictionaryDtoResponse directoryDtoResponse = directoryService.setImage(token, id, file);
        return ResponseEntity.ok(directoryDtoResponse);
    }

    @GetMapping("/key/{key}")
    public String getValueByKey(@PathVariable String key) {
        String dictionaryValue = directoryService.getValueByKey(key);
        return dictionaryValue;
    }

}
