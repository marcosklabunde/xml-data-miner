package com.ccee.dataminer.controller;

import com.ccee.dataminer.message.ResponseMessage;
import com.ccee.dataminer.service.AgentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "file")
public class FileController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private AgentService agentService;

    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadAgentXmlFile(@RequestParam("file") MultipartFile xmlFile) {
        String message = "Arquivo recebido!";

        try {
            agentService.saveAgentListFromXml(xmlFile);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            message = "Erro no processamento do arquivo!";
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessage(message));
        }

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
    }
}
