package com.hydroyura.prodms.files.server.props;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "minio")
@Data
public class MinioProps {

    private String url;
    private String accessKey;
    private String accessSecret;

}
