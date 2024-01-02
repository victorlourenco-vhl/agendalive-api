package com.agendalive.services;

import com.agendalive.domains.Live;
import com.agendalive.repositories.LiveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class LiveService {

    @Autowired
    private LiveRepository repository;

    public Live findById(UUID id) {
        return repository.findById(id).orElseThrow();
    }

    public Page<Live> findAll(PageRequest pageRequest) {
        return repository.findAll(pageRequest);
    }

    public Live create(Live obj) {
        obj.setRegistrationDate(LocalDateTime.now());
        return repository.save(obj);
    }

    public void update(UUID id, Live obj) {
        findById(id);
        obj.setId(id);
        repository.save(obj);
    }

    public void delete(UUID id) {
        findById(id);
        repository.deleteById(id);
    }

}
