package com.hydroyura.prodms.files.server.controller.swagger;

import com.hydroyura.prodms.common.model.api.ApiRes;
import com.hydroyura.prodms.files.server.api.drawings.params.GetLatestParams;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DrawingsController implements DrawingsDocumentedController {



    @Override
    @RequestMapping(method = RequestMethod.GET, value = "/api/v1/drawings/{number}")
    public ResponseEntity<ApiRes<?>> getLatest(String number, GetLatestParams params) {
        return null;
    }

    @Override
    public ResponseEntity<ApiRes<?>> getAll(String number, String parent) {
        return null;
    }

    @Override
    public ResponseEntity<ApiRes<?>> add(String number) {
        return null;
    }
}
