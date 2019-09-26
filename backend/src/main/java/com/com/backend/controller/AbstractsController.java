package com.com.backend.controller;

import com.com.backend.model.Abstracts;
import com.com.backend.service.AbstractsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/abstracts")
public class AbstractsController {

    @Autowired
    private AbstractsService abstractsService;

    @GetMapping("/allUserAbstracts")
    public ResponseEntity AbstractsForUser(@RequestHeader(value = "Authorization")String token) {
        List<Abstracts> abstractsList = abstractsService.getAllAbstractUser(token);
        return ResponseEntity.ok(abstractsList);
    }
}
