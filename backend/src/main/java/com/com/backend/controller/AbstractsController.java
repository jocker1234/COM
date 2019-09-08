package com.com.backend.controller;

import com.com.backend.domain.Abstracts;
import com.com.backend.domain.enums.Errors;
import com.com.backend.dto.AbstractsDto;
import com.com.backend.exception.AbstractNotFoundException;
import com.com.backend.exception.AppException;
import com.com.backend.exception.WrongValueException;
import com.com.backend.service.AbstractsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200")
public abstract class AbstractsController<T extends AbstractsDto, S extends Abstracts> {

    public abstract AbstractsService<T, S> getService();

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACTIVE_PARTICIPANT', 'ROLE_PASSIVE_PARTICIPANT')")
    public ResponseEntity<List<T>> getAll() {
        List<T> t = getService().getAll();
        return ResponseEntity.ok(t);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACTIVE_PARTICIPANT', 'ROLE_PASSIVE_PARTICIPANT')")
    public ResponseEntity<T> getOne(@PathVariable Long id) throws AbstractNotFoundException {
        T t = getService().getOne(id);
        return ResponseEntity.ok(t);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ACTIVE_PARTICIPANT')")
    public ResponseEntity<T> create(@RequestBody T t) throws WrongValueException {
        T result = getService().create(t);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ACTIVE_PARTICIPANT')")
    public ResponseEntity<T> update(@PathVariable Long id, @RequestBody T t) throws AppException {
        T result = getService().update(id, t);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ACTIVE_PARTICIPANT')")
    public ResponseEntity<?> delete(@PathVariable Long id) throws AbstractNotFoundException {
        getService().delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/save")
    @PreAuthorize("hasAnyRole('ROLE_ACTIVE_PARTICIPANT')")
    public ResponseEntity save(@PathVariable Long id, @RequestBody T t) throws AppException {
        T result = getService().update(id, t);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}/send")
    @PreAuthorize("hasAnyRole('ROLE_ACTIVE_PARTICIPANT')")
    public ResponseEntity sendToApproval(@PathVariable Long id) throws AppException {
        int result = getService().forwardForApproval(id);
        if (result == 0)
            throw new AbstractNotFoundException(Errors.NOT_FOUND);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/accept")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity approvedAbstract(@PathVariable Long id) throws AppException {
        int result = getService().approved(id);
        if(result == 0)
            throw new AbstractNotFoundException(Errors.NOT_FOUND);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/reject")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity rejectedAbstract(@PathVariable Long id) throws AppException {
        int result = getService().rejected(id);
        if(result == 0)
            throw new AbstractNotFoundException(Errors.NOT_FOUND);
        return ResponseEntity.ok().build();
    }



}
