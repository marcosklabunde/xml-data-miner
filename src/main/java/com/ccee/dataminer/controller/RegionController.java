package com.ccee.dataminer.controller;

import com.ccee.dataminer.model.Acronym;
import com.ccee.dataminer.model.Region;
import com.ccee.dataminer.service.RegionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "region")
public class RegionController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private RegionService regionService;

    @GetMapping("/list")
    public ResponseEntity<List<Region>> getRegions(@RequestParam(required = false) Acronym acronym) {
        try {
            List<Region> regionList = regionService.findRegions(acronym);

            if (regionList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(regionList, HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
