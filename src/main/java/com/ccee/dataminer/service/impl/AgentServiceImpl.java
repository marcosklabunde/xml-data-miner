package com.ccee.dataminer.service.impl;

import com.ccee.dataminer.model.Agent;
import com.ccee.dataminer.parser.impl.AgentXmlParser;
import com.ccee.dataminer.repository.AgentRepository;
import com.ccee.dataminer.service.AgentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.util.List;

@Service
public class AgentServiceImpl implements AgentService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private AgentRepository agentRepository;

    @Autowired
    private AgentXmlParser agentXmlParser;

    @Override
    public void saveAgentListFromXml(MultipartFile xmlFile) throws IOException, XMLStreamException {
        List<Agent> parsedAgents = agentXmlParser.parseXmlData(xmlFile.getInputStream());
        agentRepository.saveAll(parsedAgents);
    }

    @Override
    public List<Agent> findAgents() {
        return agentRepository.findAll();
    }
}
