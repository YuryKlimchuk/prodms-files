package com.hydroyura.prodms.files.server.api.res;

import com.hydroyura.prodms.files.server.api.enums.DrawingType;
import java.util.HashMap;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetUrlsLatestRes {

    private Map<DrawingType, String> drawings = new HashMap<>();

}
