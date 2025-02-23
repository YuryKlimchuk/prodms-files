package com.hydroyura.prodms.files.server.service.filename;

import com.hydroyura.prodms.files.server.api.req.AddFileReq;
import org.springframework.stereotype.Service;

@Service
public class DefaultFileNameBuilderStrategy implements FileNameBuilderStrategy {

    private final static String PATTERN = "%s___%s.pfg";

    @Override
    public String build(String number, AddFileReq req) {
        return PATTERN.formatted(number, req.getType().getCode());
    }
}
