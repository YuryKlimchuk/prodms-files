package com.hydroyura.prodms.files.server.controller.swagger;

import com.hydroyura.prodms.files.server.api.ApiRes;
import com.hydroyura.prodms.files.server.api.drawings.params.GetLatestParams;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/v1/drawings")
public class DrawingsController implements DrawingsDocumentedController {



    @Override
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
