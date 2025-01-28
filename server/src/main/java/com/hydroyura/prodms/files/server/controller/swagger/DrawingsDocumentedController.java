package com.hydroyura.prodms.files.server.controller.swagger;


import com.hydroyura.prodms.common.model.api.ApiRes;
import com.hydroyura.prodms.files.server.api.drawings.params.GetLatestParams;
import com.hydroyura.prodms.files.server.api.res.GetLatestRes;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public interface DrawingsDocumentedController {


    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            content = {@Content(schema = @Schema(implementation = GetLatestSuccess.class))},
            description = "Success"
        )
    })
    ResponseEntity<ApiRes<?>> getLatest(String number, GetLatestParams params);

    class GetLatestSuccess extends ApiRes<GetLatestRes> {}


    @RequestMapping(method = RequestMethod.GET, value = "api/v1/drawings/{number}/all")
    ResponseEntity<ApiRes<?>> getAll(String number, String parent);

    @RequestMapping(method = RequestMethod.POST, value = "api/v1/drawings/{number}")
    ResponseEntity<ApiRes<?>> add(String number);


}
