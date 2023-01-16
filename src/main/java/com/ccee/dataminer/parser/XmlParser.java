package com.ccee.dataminer.parser;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.InputStream;
import java.util.List;

public interface XmlParser<T> {
    List<T> parseXmlData(InputStream fileStream) throws XMLStreamException;

    void onStartElement(XMLStreamReader reader) throws XMLStreamException;

    void onEndElement(XMLStreamReader reader) throws XMLStreamException;
}
