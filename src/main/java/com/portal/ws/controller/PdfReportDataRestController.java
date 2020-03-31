package com.portal.ws.controller;

import com.portal.ws.model.PdfFileRequest;
import com.portal.ws.model.PdfFileResponse;
import com.portal.ws.service.WebServiceCallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/pdfreportdata")
public class PdfReportDataRestController {
    @Autowired
    private WebServiceCallService service;

    @GetMapping("reportfiledata")
    public PdfFileResponse getReportFileData(@RequestBody PdfFileRequest request){
        return service.getReportFileData(request);
    }
}
