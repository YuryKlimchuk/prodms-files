package com.hydroyura.prodms.files.server.service.filename;

import com.hydroyura.prodms.files.server.api.req.AddFileReq;

public interface FileNameBuilderStrategy {

    String build(String number, AddFileReq req);

}
