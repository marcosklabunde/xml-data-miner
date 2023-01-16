package com.ccee.dataminer.service;

import com.ccee.dataminer.model.Agent;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.util.List;

public interface AgentService {
    void saveAgentListFromXml(MultipartFile xmlFile) throws IOException, XMLStreamException;

    List<Agent> findAgents();
}
