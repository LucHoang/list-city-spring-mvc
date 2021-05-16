package com.hsl.repository;

import com.hsl.model.Nation;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface INationRepository extends PagingAndSortingRepository<Nation, Long> {
}