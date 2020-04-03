package com.portal.ws.controller;

import com.portal.ws.model.*;
import com.portal.ws.service.WebServiceCallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/rest/portal")
public class PortalRestController {

    @Autowired
    private WebServiceCallService service;
    @PostMapping("informstate")
    public InformStateResponse informState(@Valid @RequestBody InformStateRequest request){
        return service.informState(request);
    }

    @PostMapping("informfilemetadata")
    public InformFileResponse informFileMetaData(@RequestBody InformFileRequest request){
        return service.informFileMetaData(request);
    }

    @PostMapping("deletefilemetadata")
    public DeleteFileMetaResponse deleteFileMetaData(@RequestBody DeleteFileMetaRequest request){
        return service.deleteFileMetaData(request);
    }
}
