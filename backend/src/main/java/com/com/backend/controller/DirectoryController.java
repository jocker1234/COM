package com.com.backend.controller;

import com.com.backend.dto.request.DirectoryDtoRequest;
import com.com.backend.dto.response.DirectoryDtoResponse;
import com.com.backend.exception.AccessException;
import com.com.backend.exception.AppException;
import com.com.backend.service.DirectoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(value = {"http://localhost:4200", "http://54.37.234.192:4200"})
@RequestMapping("/api/directory")
public class DirectoryController {

    private DirectoryService directoryService;

    public DirectoryController(DirectoryService directoryService) {
        this.directoryService = directoryService;
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<DirectoryDtoResponse>> getAll(@RequestHeader(value = "Authorization")String token)
                                                                                                throws AccessException {
        List<DirectoryDtoResponse> directoryDtoResponses = directoryService.getAll(token);
        return ResponseEntity.ok(directoryDtoResponses);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<DirectoryDtoResponse> getOne(@RequestHeader(value = "Authorization")String token,
                                                                            @PathVariable Long id) throws AppException {
        DirectoryDtoResponse directoryDtoResponse = directoryService.getOne(id, token);
        return ResponseEntity.ok(directoryDtoResponse);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<DirectoryDtoResponse> updateParameter(@RequestHeader(value = "Authorization")String token,
                    @PathVariable Long id, @RequestBody DirectoryDtoRequest directoryDtoRequest) throws AccessException {
        DirectoryDtoResponse directoryDtoResponse = directoryService.updataParameter(token, id, directoryDtoRequest);
        return ResponseEntity.ok(directoryDtoResponse);
    }

}
