package com.hydroyura.prodms.files.server.controller.swagger;

import com.hydroyura.prodms.common.model.api.ApiRes;
import com.hydroyura.prodms.files.server.api.drawings.params.GetLatestParams;
import com.hydroyura.prodms.files.server.api.res.GetLatestRes;
import com.hydroyura.prodms.files.server.service.DrawingsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DrawingsController implements DrawingsDocumentedController {


    private final DrawingsService drawingsService;

    @Override
    @RequestMapping(method = RequestMethod.GET, value = "/api/v1/drawings/{number}")
    public ResponseEntity<ApiRes<?>> getLatest(String number, GetLatestParams params) {
        ApiRes<GetLatestRes> apiRes = new ApiRes<>();
        var result = drawingsService.getLatest(number, params);
        // TODO: fix system.out.println
        result.ifPresentOrElse(apiRes::setData, () -> System.out.println("123"));
        return result.isPresent()
            ? ResponseEntity.ok(apiRes)
            : new ResponseEntity<>(apiRes, HttpStatus.NOT_FOUND);
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
