package com.ccee.dataminer.controller;

import com.ccee.dataminer.model.Agent;
import com.ccee.dataminer.service.AgentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "agent")
public class AgentController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private AgentService agentService;

    @GetMapping("/list")
    public ResponseEntity<List<Agent>> getAgents() {
        try {
            List<Agent> agentList = agentService.findAgents();

            if (agentList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(agentList, HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
