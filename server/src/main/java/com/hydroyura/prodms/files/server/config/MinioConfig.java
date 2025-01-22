package com.hydroyura.prodms.files.server.config;

import com.hydroyura.prodms.files.server.props.MinioProps;
import io.minio.GetObjectArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.UploadObjectArgs;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.KeyStore;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
public class MinioConfig {


    @Bean
    OkHttpClient okHttpClient(MinioProps props) {
        var httpClientBuilder = new OkHttpClient.Builder();
        insertSslContext(httpClientBuilder, props);
        return httpClientBuilder.build();
    }

    @Bean
    MinioClient minioClient(MinioProps props, OkHttpClient httpClient) {
        return MinioClient.builder()
            .endpoint(props.getUrl())
            //.httpClient(httpClient)
            .credentials(props.getAccessKey(), props.getAccessSecret())
            .build();
    }

    @Bean
    String test(MinioClient minioClient) throws Exception {

        minioClient.makeBucket(
            MakeBucketArgs
                .builder()
                .bucket("user1")
                .build());

        try (InputStream stream =
                 minioClient.getObject(GetObjectArgs
                     .builder()
                     .bucket("user1")
                     .object("example.pdf")
                     .build())) {
            // Read the stream
        }

        minioClient.uploadObject(
            UploadObjectArgs.builder()
                .bucket("user1")
                .object("application.yaml")
                .filename("/home/klimchuk/IdeaProjects/prodms/prodms-files/server/target/classes/application.yaml")
                .build()
        );

        new ClassPathResource("application.yaml").getInputStream();

        return "";
    }


    private void insertSslContext(OkHttpClient.Builder builder, MinioProps props) {
        try (var is = new ByteArrayInputStream(props.getCert().getBytes(StandardCharsets.UTF_8))) {
            CertificateFactory certFactory = CertificateFactory.getInstance("X.509");
            X509Certificate cert = (X509Certificate) certFactory.generateCertificate(is);
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(null, null);
            keyStore.setCertificateEntry("MINIO_CACERT", cert);
            TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            tmf.init(keyStore);
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, tmf.getTrustManagers(), null);
            builder.sslSocketFactory(sslContext.getSocketFactory(), (X509TrustManager) tmf.getTrustManagers()[0]);
        } catch (Exception e) {
            // TODO: create custom ex
            throw new RuntimeException("");
        }
    }


}
