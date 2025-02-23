package com.hydroyura.prodms.files.server.api.req;

import com.hydroyura.prodms.files.server.api.enums.DrawingType;
import lombok.Data;

@Data
public class AddFileReq {

    private DrawingType type;
    private byte[] bytes;

}
