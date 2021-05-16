package com.hsl.service;

import com.hsl.model.Nation;
import com.hsl.repository.INationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NationService implements INationService {
    @Autowired
    private INationRepository nationRepository;

    @Override
    public Iterable<Nation> findAll() {
        return nationRepository.findAll();
    }

    @Override
    public Optional<Nation> findById(Long id) {
        return nationRepository.findById(id);
    }

    @Override
    public void save(Nation nation) {
        nationRepository.save(nation);
    }

    @Override
    public void remove(Long id) {
        nationRepository.deleteById(id);
    }
}