package com.portal.ws.service;

import com.portal.ws.model.*;
import com.portal.ws.util.FileUtilities;
import com.portal.ws.util.SoapCallUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.w3c.dom.Node;

import javax.annotation.PostConstruct;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

@Service
public class WebServiceCallService {

    private static String reportFileDataXmlString;
    private static String informStateXmlString;
    private static String informFileMetaDataXmlFileString;
    private static String deleteFileMetaXmlFileString;
    private static String tokenPortalWsAccessXmlFileString;

    private final static String SUCCESS_MESSAGE ="SUCCESS";

    @Value( "${pdfreportws.credentials.username}" )
    private String pdfReportWsUserName;
    @Value( "${pdfreportws.credentials.password}" )
    private String pdfReportWsPassword;
    @Value( "${pdfreportws.soapEndpointUrl}" )
    private String pdfReportWsEndpointUrl;
    @Value( "${pdfreportws.actions.getfile}" )
    private String pdfReportWsGetFileSoapAction;
    @Value( "${portalws.soapEndpointUrl}" )
    private String portalWsEndpointUrl;
    @Value( "${portalws.actions.informstate}" )
    private String portalWsInformStateSoapAction;
    @Value( "${portalws.actions.informfilemetadata}" )
    private String portalWsInformFileMetaDataSoapAction;
    @Value( "${portalws.actions.deletefilemetadata}" )
    private String portalWsDeleteFileMetaDataSoapAction;
    @Value( "${portalws.actions.getToken}" )
    private String portalWsgetTokenSoapAction;

    @PostConstruct
    private void readXmlFiles(){
        reportFileDataXmlString = FileUtilities.readXmlFileString("reportFileDataXmlFile.xml");
        informStateXmlString = FileUtilities.readXmlFileString("informStateXmlFile.xml");
        informFileMetaDataXmlFileString = FileUtilities.readXmlFileString("informFileMetaDataXmlFile.xml");
        deleteFileMetaXmlFileString = FileUtilities.readXmlFileString("deleteFileMetaXmlFile.xml");
        tokenPortalWsAccessXmlFileString = FileUtilities.readXmlFileString("tokenPortalWsAccessXmlFile.xml");
    }

    @Autowired
    private SoapCallUtility soapCall;

    private static UUID ticketPortalWs;

    public void getTicketPortalWs() throws Exception{
        SOAPMessage soapResponse = soapCall.callSoapWebService(portalWsEndpointUrl,portalWsgetTokenSoapAction,tokenPortalWsAccessXmlFileString,null);
        String responseString = soapResponse.getSOAPBody().getChildNodes().item(0).getTextContent();
        ticketPortalWs= UUID.fromString(responseString);
    };

    public PdfFileResponse getReportFileData(PdfFileRequest request){
        List<SoapParameterModel> parameterList = new ArrayList<>();
        parameterList.add(new SoapParameterModel("entityTypeValue",request.getEntityType()));
        parameterList.add(new SoapParameterModel("entityIdValue",request.getEntityId()+""));
        parameterList.add(new SoapParameterModel("usernameValue",pdfReportWsUserName));
        parameterList.add(new SoapParameterModel("passwordValue",pdfReportWsPassword));

        PdfFileResponse result = new PdfFileResponse();
        try {
            SOAPMessage soapResponse = soapCall.callSoapWebService(pdfReportWsEndpointUrl,pdfReportWsGetFileSoapAction,reportFileDataXmlString,parameterList);
            String responseString = soapResponse.getSOAPBody().getChildNodes().item(0).getTextContent();
            byte[] utf8Bytes = Base64.getDecoder().decode(responseString);
            result.setBlobData(utf8Bytes);
            result.setMessage(SUCCESS_MESSAGE);
            FileUtilities.writeBytesToFileClassic(utf8Bytes,"File_" + request.getEntityId()+".pdf");
        } catch (SOAPException e) {
            result.setMessage(e.getMessage());
        } catch (FileNotFoundException e) {
            result.setMessage(e.getMessage());
        }catch (Exception ex){
            result.setMessage(ex.getMessage());
        }
        return result;
    }

    public InformStateResponse informState(InformStateRequest request){
        List<SoapParameterModel> parameterList = new ArrayList<>();
        parameterList.add(new SoapParameterModel("tokenIdValue",ticketPortalWs.toString()));
        parameterList.add(new SoapParameterModel("idValue",request.getId()+""));
        parameterList.add(new SoapParameterModel("stateValue",request.getState()));
        parameterList.add(new SoapParameterModel("stateUpdateTimeValue",request.getStateUpdateTime()));

        InformStateResponse result = new InformStateResponse(false,null);
        try {
            SOAPMessage soapResponse = soapCall.callSoapWebService(portalWsEndpointUrl,portalWsInformStateSoapAction,informStateXmlString,parameterList);
            Node responseNode = soapResponse.getSOAPBody().getChildNodes().item(0);
            result.setMessage(responseNode.getChildNodes().item(0).getChildNodes().item(0).getTextContent());
            result.setResult(Boolean.parseBoolean(responseNode.getChildNodes().item(0).getChildNodes().item(1).getTextContent()));
            result.setMessage(SUCCESS_MESSAGE);
        } catch (SOAPException e) {
            result.setMessage(e.getMessage());
        } catch (FileNotFoundException e) {
            result.setMessage(e.getMessage());
        }catch (Exception ex){
            result.setMessage(ex.getMessage());
        }
        return result;
    }

    public InformFileResponse informFileMetaData(InformFileRequest request){
        List<SoapParameterModel> parameterList = new ArrayList<>();
        parameterList.add(new SoapParameterModel("tokenIdValue",ticketPortalWs.toString()));
        parameterList.add(new SoapParameterModel("relatedEntityId",request.getRelatedEntityId()+""));
        parameterList.add(new SoapParameterModel("fileName",request.getFileName()));
        parameterList.add(new SoapParameterModel("fileMetaDataId",request.getFileMetaDataId().toString()));
        parameterList.add(new SoapParameterModel("fileId",request.getFileId()));

        InformFileResponse result = new InformFileResponse();
        try {
            SOAPMessage soapResponse = soapCall.callSoapWebService(portalWsEndpointUrl,portalWsInformFileMetaDataSoapAction,informFileMetaDataXmlFileString,parameterList);
            Node responseNode = soapResponse.getSOAPBody().getChildNodes().item(0);
            result.setResult(Boolean.parseBoolean(responseNode.getTextContent()));
            result.setMessage(SUCCESS_MESSAGE);
        } catch (SOAPException e) {
            result.setMessage(e.getMessage());
        } catch (FileNotFoundException e) {
            result.setMessage(e.getMessage());
        }catch (Exception ex){
            result.setMessage(ex.getMessage());
        }
        return result;
    }

    public DeleteFileMetaResponse deleteFileMetaData(DeleteFileMetaRequest request){
        List<SoapParameterModel> parameterList = new ArrayList<>();
        parameterList.add(new SoapParameterModel("tokenIdValue",ticketPortalWs.toString()));
        parameterList.add(new SoapParameterModel("fileId",request.getFileId()));

        DeleteFileMetaResponse result = new DeleteFileMetaResponse();
        try {
            SOAPMessage soapResponse = soapCall.callSoapWebService(portalWsEndpointUrl,portalWsDeleteFileMetaDataSoapAction,deleteFileMetaXmlFileString,parameterList);
            Node responseNode = soapResponse.getSOAPBody().getChildNodes().item(0);
            result.setResult(Boolean.parseBoolean(responseNode.getTextContent()));
            result.setMessage(SUCCESS_MESSAGE);
        } catch (SOAPException e) {
            result.setMessage(e.getMessage());
        } catch (FileNotFoundException e) {
            result.setMessage(e.getMessage());
        }catch (Exception ex){
            result.setMessage(ex.getMessage());
        }
        return result;
    }
}
