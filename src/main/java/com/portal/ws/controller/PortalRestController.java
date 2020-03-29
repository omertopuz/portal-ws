package com.portal.ws.controller;

import com.portal.ws.model.*;
import com.portal.ws.service.WebServiceCallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/portal")
public class PortalRestController {

    @Autowired
    private WebServiceCallService service;

    @GetMapping("reportfiledata")
    public PdfFileResponse getReportFileData(@RequestBody PdfFileRequest request){
        return service.getReportFileData(request);
    }

    @GetMapping("informstate")
    public InfromStateResponse informState(@RequestBody InformStateRequest request){
        return service.informState(request);
    }

    @GetMapping("informfilemetadata")
    public InformFileResponse informFileMetaData(@RequestBody InformFileRequest request){
        return service.informFileMetaData(request);
    }

    @GetMapping("deletefilemetadata")
    public DeleteFileMetaResponse deleteFileMetaData(@RequestBody DeleteFileMetaRequest request){
        return service.deleteFileMetaData(request);
    }
}
