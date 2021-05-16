package com.hsl.service;

import com.hsl.model.City;
import com.hsl.model.Nation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICityService extends IGeneralService<City> {
    Iterable<City> findAllByNation(Nation nation);
    Page<City> findAll(Pageable pageable);
    Page<City> findAllByNameContaining(String name, Pageable pageable);
}