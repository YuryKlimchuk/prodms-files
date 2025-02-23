package com.hydroyura.prodms.files.server.controller.swagger;

import com.hydroyura.prodms.common.model.api.ApiRes;
import com.hydroyura.prodms.files.server.api.drawings.params.GetLatestParams;
import com.hydroyura.prodms.files.server.api.req.AddFileReq;
import com.hydroyura.prodms.files.server.api.res.GetUrlsLatestRes;
import com.hydroyura.prodms.files.server.service.DrawingsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DrawingsController implements DrawingsDocumentedController {


    private final DrawingsService drawingsService;

    @Override
    @RequestMapping(method = RequestMethod.GET, value = "/api/v1/drawings/{number}")
    public ResponseEntity<ApiRes<?>> getFiles(String number, GetLatestParams params) {
        ApiRes<GetUrlsLatestRes> apiRes = new ApiRes<>();
        var result = drawingsService.getFiles(number, params);
        // TODO: fix system.out.println
        result.ifPresentOrElse(apiRes::setData, () -> System.out.println("123"));
        return result.isPresent()
            ? ResponseEntity.ok(apiRes)
            : new ResponseEntity<>(apiRes, HttpStatus.NOT_FOUND);
     }

    @Override
    @RequestMapping(method = RequestMethod.POST, value = "/api/v1/drawings/{number}")
    public ResponseEntity<ApiRes<?>> addFile(String number, @RequestBody AddFileReq req) {
        drawingsService.addFile(number, req);
        ApiRes<GetUrlsLatestRes> apiRes = new ApiRes<>();
        return ResponseEntity.ok(apiRes);
    }
}
