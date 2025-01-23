package com.hydroyura.prodms.files.server.controller.swagger;


import com.hydroyura.prodms.files.server.api.ApiRes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public interface DrawingsDocumentedController {


    @RequestMapping(method = RequestMethod.GET, value = "api/v1/drawings/{number}")
    ResponseEntity<ApiRes<?>> getLatest(String number, String parent);

    @RequestMapping(method = RequestMethod.GET, value = "api/v1/drawings/{number}/all")
    ResponseEntity<ApiRes<?>> getAll(String number, String parent);

    @RequestMapping(method = RequestMethod.POST, value = "api/v1/drawings/{number}")
    ResponseEntity<ApiRes<?>> add(String number);


}
