package com.hsl.repository;

import com.hsl.model.City;
import com.hsl.model.Nation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICityRepository extends PagingAndSortingRepository<City, Long> {
    Iterable<City> findAllByNation(Nation nation);
    Page<City> findAllByNameContaining(String ame, Pageable pageable);
}