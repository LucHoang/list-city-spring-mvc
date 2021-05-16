package com.hsl.service;

import com.hsl.model.City;
import com.hsl.model.Nation;
import com.hsl.repository.ICityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CityService implements ICityService {
    @Autowired
    private ICityRepository cityRepository;

    @Override
    public Page<City> findAll(Pageable pageable) {
        return cityRepository.findAll(pageable);
    }

    @Override
    public Iterable<City> findAll() {
        return cityRepository.findAll();
    }

    @Override
    public Optional<City> findById(Long id) {
        return cityRepository.findById(id);
    }

    @Override
    public void save(City City) {
        cityRepository.save(City);
    }

    @Override
    public void remove(Long id) {
        cityRepository.deleteById(id);
    }

    @Override
    public Iterable<City> findAllByNation(Nation nation) {
        return cityRepository.findAllByNation(nation);
    }

    @Override
    public Page<City> findAllByNameContaining(String name, Pageable pageable) {
        return cityRepository.findAllByNameContaining(name, pageable);
    }
}