package com.com.backend.controller;

import com.com.backend.dto.response.AbstractsDtoResponse;
import com.com.backend.model.Abstracts;
import com.com.backend.model.enums.ExceptionType;
import com.com.backend.dto.request.AbstractsDtoRequest;
import com.com.backend.exception.AbstractNotFoundException;
import com.com.backend.exception.AppException;
import com.com.backend.model.enums.Status;
import com.com.backend.service.AbstractsAbstractService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("http://localhost:4200")
public abstract class AbstractsAbstractController<TREQ extends AbstractsDtoRequest, TRES extends AbstractsDtoResponse,
                                                                                                S extends Abstracts> {

    public abstract AbstractsAbstractService<TREQ, TRES, S> getService();

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACTIVE_PARTICIPANT', 'ROLE_PASSIVE_PARTICIPANT')")
    public ResponseEntity<List<TRES>> getAll() {
        List<TRES> t = getService().getAll();
        return ResponseEntity.ok(t);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACTIVE_PARTICIPANT', 'ROLE_PASSIVE_PARTICIPANT')")
    public ResponseEntity<TRES> getOne(@PathVariable Long id) throws AbstractNotFoundException {
        TRES t = getService().getOne(id);
        return ResponseEntity.ok(t);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ACTIVE_PARTICIPANT')")
    public ResponseEntity<TRES> create(@RequestBody TREQ t, @RequestHeader(value = "Authorization")String token) throws AppException {
        TRES result = getService().create(t, token);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ACTIVE_PARTICIPANT')")
    public ResponseEntity<TRES> update(@PathVariable Long id, @RequestBody TREQ t) throws AppException {
        TRES result = getService().update(id, t);
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
    public ResponseEntity save(@PathVariable Long id, @RequestBody TREQ t) throws AppException {
        TRES result = getService().update(id, t);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}/send")
    @PreAuthorize("hasAnyRole('ROLE_ACTIVE_PARTICIPANT')")
    public ResponseEntity sendToApproval(@PathVariable Long id) throws AppException {
        int result = getService().forwardForApproval(id);
        if (result == 0)
            throw new AbstractNotFoundException(ExceptionType.NOT_FOUND);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}/rejectApprove")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity rejectionApproved(@PathVariable Long id, @RequestBody Map<String, String> params) throws AppException {
        String status = params.get("status");
        if(!(Status.APPROVED.getStatus().equals(status) || Status.REJECTED.getStatus().equals(status))){
            throw new AppException(ExceptionType.WRONG_STATUS);
        }
        getService().changeStatus(id, status);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/accept")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity approvedAbstract(@PathVariable Long id) throws AppException {
        int result = getService().approved(id);
        if(result == 0)
            throw new AbstractNotFoundException(ExceptionType.NOT_FOUND);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/reject")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity rejectedAbstract(@PathVariable Long id) throws AppException {
        int result = getService().rejected(id);
        if(result == 0)
            throw new AbstractNotFoundException(ExceptionType.NOT_FOUND);
        return ResponseEntity.ok().build();
    }

}
