package com.ccee.dataminer.service.impl;

import com.ccee.dataminer.model.Acronym;
import com.ccee.dataminer.model.Region;
import com.ccee.dataminer.repository.RegionRepository;
import com.ccee.dataminer.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class RegionServiceImpl implements RegionService {

    @Autowired
    private RegionRepository regionRepository;

    @Override
    public List<Region> findRegions(Acronym acronym) {
        List<Region> regionList;

        if (Objects.isNull(acronym)) {
            regionList = regionRepository.findAll();
        } else {
            regionList = regionRepository.findByAcronym(acronym);
        }

        //Avarage Price data is confidential
        regionList.forEach(region -> region.setAveragePrice(new ArrayList<>()));
        return regionList;
    }
}
