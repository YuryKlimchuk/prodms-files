package com.hydroyura.prodms.files.server.api.res;

import com.hydroyura.prodms.files.server.api.enums.DrawingType;
import java.util.HashMap;
import java.util.Map;
import lombok.Data;

@Data
public class GetLatestRes {

    Map<DrawingType, String> drawings = new HashMap<>();

}
