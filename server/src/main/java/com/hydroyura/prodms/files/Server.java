package com.hydroyura.prodms.files;

import com.hydroyura.prodms.files.props.MinioProps;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(MinioProps.class)
public class Server {

    public static void main(String[] args) {
        SpringApplication.run(Server.class, args);
    }

}