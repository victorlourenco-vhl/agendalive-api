package com.agendalive.controllers;

import com.agendalive.domains.Live;
import com.agendalive.services.LiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "lives")
public class LiveController {

    @Autowired
    private LiveService service;

    @GetMapping("{id}")
    private ResponseEntity<Live> findById(@PathVariable UUID id) {
        Live live = service.findById(id);
        return new ResponseEntity<>(live, HttpStatus.OK);
    }

    @GetMapping
    private ResponseEntity<Page<Live>> findAll(@RequestParam(defaultValue = "0", required = false) Integer pageNumber,
                                               @RequestParam(defaultValue = "10", required = false) Integer pageSize,
                                               @RequestParam(defaultValue = "id", required = false) String sortBy) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).ascending());
        Page<Live> lives = service.findAll(pageRequest);
        return new ResponseEntity<>(lives, HttpStatus.OK);
    }

    @PostMapping
    private ResponseEntity<Live> create(@RequestBody Live live) {
        live = service.create(live);
        return new ResponseEntity<>(live, HttpStatus.CREATED);
    }

    @PostMapping("{id}")
    private ResponseEntity<Void> update(@PathVariable UUID id, @RequestBody Live live) {
        service.update(id, live);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("{id}")
    private ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
