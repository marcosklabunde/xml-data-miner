package com.ccee.dataminer.repository;

import com.ccee.dataminer.model.Acronym;
import com.ccee.dataminer.model.Region;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RegionRepository extends JpaRepository<Region, Long> {
    List<Region> findByAcronym(Acronym acronym);
}
