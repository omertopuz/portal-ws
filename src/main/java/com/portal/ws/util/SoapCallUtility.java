package com.portal.ws.util;

import com.portal.ws.model.SoapParameterModel;
import org.springframework.stereotype.Component;

import javax.xml.soap.*;
import javax.xml.transform.stream.StreamSource;
import java.io.FileNotFoundException;
import java.io.StringReader;
import java.util.List;

@Component
public class SoapCallUtility {

    public SOAPMessage callSoapWebService(String soapEndpointUrl, String soapAction, String requestXmlString, List<SoapParameterModel> parameters) throws SOAPException, FileNotFoundException,Exception {
        // Create SOAP Connection
        SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
        SOAPConnection soapConnection = soapConnectionFactory.createConnection();
        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();

        SOAPPart soapPart = soapMessage.getSOAPPart();
        String contents = requestXmlString;

        for (SoapParameterModel p:parameters) {
            contents = contents.replace(p.getParameterName(),p.getParameterValue());
        }

        StreamSource ss =new StreamSource(new StringReader(contents));
        // Set contents of message
        soapPart.setContent(ss);
        MimeHeaders headers = soapMessage.getMimeHeaders();
        headers.addHeader("SOAPAction", soapAction);

        soapMessage.saveChanges();
        SOAPMessage soapResponse = soapConnection.call(soapMessage, soapEndpointUrl);

        soapConnection.close();

        return soapResponse;
    }

}
