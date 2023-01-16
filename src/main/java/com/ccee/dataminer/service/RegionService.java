package com.ccee.dataminer.service;

import com.ccee.dataminer.model.Acronym;
import com.ccee.dataminer.model.Region;

import java.util.List;

public interface RegionService {
    List<Region> findRegions(Acronym acronym);
}
