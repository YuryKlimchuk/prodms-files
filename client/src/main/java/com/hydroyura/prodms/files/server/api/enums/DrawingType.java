package com.hydroyura.prodms.files.server.api.enums;

import lombok.Getter;

public enum DrawingType {

    SIMPLE("SI"), ASSEMBLY("AS"), SPECIFICATION("SP"), OVERALL("OV"), OTHER("OT");

    @Getter
    private String code;

    DrawingType(String code) {
        this.code = code;
    }

}
