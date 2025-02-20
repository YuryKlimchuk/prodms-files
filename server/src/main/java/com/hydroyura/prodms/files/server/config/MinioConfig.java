package com.hydroyura.prodms.files.server.config;

import com.hydroyura.prodms.files.server.props.MinioProps;
import io.minio.GetObjectArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.UploadObjectArgs;
import io.minio.messages.Tags;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyStore;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Map;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
public class MinioConfig{

    @Bean
    OkHttpClient okHttpClient(MinioProps props) {
        var httpClientBuilder = new OkHttpClient.Builder();
        return httpClientBuilder.build();
    }

    @Bean
    MinioClient minioClient(MinioProps props, OkHttpClient httpClient) {
        return MinioClient.builder()
            .endpoint(props.getUrl())
            .httpClient(httpClient)
            .credentials(props.getAccessKey(), props.getAccessSecret())
            .build();
    }

//    @Bean
//    String test(MinioClient minioClient) throws Exception {
//
////        minioClient.makeBucket(
////            MakeBucketArgs
////                .builder()
////                .bucket("user1")
////                .build());
//
////        try (InputStream stream =
////                 minioClient.getObject(GetObjectArgs
////                     .builder()
////                     .bucket("user1")
////                     .object("example.pdf")
////                     .build())) {
////            // Read the stream
////        }
//
////        minioClient.uploadObject(
////            UploadObjectArgs.builder()
////                .bucket("user1")
////                .object("application.yaml")
////                .filename("/home/klimchuk/IdeaProjects/prodms/prodms-files/server/target/classes/application.yaml")
////                .build()
////        );
//
//        new ClassPathResource("application.yaml").getInputStream();
//
//        putObject(minioClient, "example_3.pdf");
//
//        return "";
//    }
//
//    private InputStream file(String name) throws Exception {
//        return new ClassPathResource(name).getInputStream();
//    }
//
//    private void putObject(MinioClient client, String file) throws Exception {
//        var headers = Map.of(
//            "key1", "val1",
//            "key2", "val2",
//            "key3", "val3",
//            "key4", "val4"
//        );
//        var is = new ByteArrayInputStream(
//            Files.readAllBytes(
//                Paths.get(this.getClass().getClassLoader().getResource(file).toURI()))
//        );
//        var result = client.putObject(
//            PutObjectArgs.builder()
//                .bucket("user1")
//                .object(file)
//                .tags(Tags.newObjectTags(Map.of("tag1","vTag1", "tag2", "vTag2")))
//                .userMetadata(headers)
//                .stream(is, is.available(), -1)
//                .build()
//        );
//        is.close();
//        int a = 1;
//    }

}
