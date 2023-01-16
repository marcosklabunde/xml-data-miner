package com.ccee.dataminer.parser.impl;

import com.ccee.dataminer.model.Acronym;
import com.ccee.dataminer.model.Agent;
import com.ccee.dataminer.model.Region;
import com.ccee.dataminer.parser.XmlParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@Component
public class AgentXmlParser implements XmlParser<Agent> {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private List<Agent> result = new ArrayList<>();

    private Agent currAgent = new Agent();

    private Region currRegion = new Region();

    private List<BigDecimal> valueList = new ArrayList<>();

    @Override
    public List<Agent> parseXmlData(InputStream fileStream) throws XMLStreamException {
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        XMLStreamReader reader = xmlInputFactory.createXMLStreamReader(fileStream);
        long startTime = System.currentTimeMillis();

        try {
            while (reader.hasNext()) {
                int eventType = reader.next();
                if (eventType == XMLStreamReader.START_ELEMENT) {
                    onStartElement(reader);
                } else if (eventType == XMLStreamReader.END_ELEMENT) {
                    onEndElement(reader);
                }
            }
        } finally {
            reader.close();
        }

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        logger.info("XML File parsed in " + totalTime + "ms.");

        return result;
    }

    @Override
    public void onStartElement(XMLStreamReader reader) throws XMLStreamException {
        XmlElement currElement = XmlElement.valueOf(reader.getLocalName().toUpperCase());

        switch (currElement) {
            case AGENTE:
                currAgent = new Agent();
                break;
            case CODIGO:
                currAgent.setCode(Long.parseLong(reader.getElementText()));
                break;
            case DATA:
                currAgent.setDate(parseDate(reader.getElementText()));
                break;
            case REGIAO:
                currRegion = new Region();
                currRegion.setAcronym(Acronym.valueOf(reader.getAttributeValue(0)));
                break;
            case GERACAO:
            case COMPRA:
            case PRECOMEDIO:
                valueList = new ArrayList<>();
                break;
            case VALOR:
                valueList.add(new BigDecimal(reader.getElementText().replace(".", "")));
                break;
        }
    }

    @Override
    public void onEndElement(XMLStreamReader reader) {
        XmlElement currElement = XmlElement.valueOf(reader.getLocalName().toUpperCase());

        switch (currElement) {
            case AGENTE:
                result.add(currAgent);
                System.out.println("Agente recebido: " + currAgent.getCode());
                break;
            case REGIAO:
                currAgent.getRegionList().add(currRegion);
                break;
            case GERACAO:
                currRegion.setGeneration(valueList);
                break;
            case COMPRA:
                currRegion.setPurchase(valueList);
                break;
            case PRECOMEDIO:
                currRegion.setAveragePrice(valueList);
                break;
        }
    }

    private GregorianCalendar parseDate(String dateToParse) throws XMLStreamException {
        try {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
            Date date = df.parse(dateToParse);
            GregorianCalendar cal = new GregorianCalendar();
            cal.setTime(date);

            return cal;
        } catch (ParseException e) {
            logger.error("Error on parsing date: " + e.getMessage(), e);
        }

        return null;
    }

    private enum XmlElement {
        AGENTES, AGENTE, CODIGO, DATA, REGIAO, SIGLA, GERACAO, COMPRA, PRECOMEDIO, VALOR;
    }
}
