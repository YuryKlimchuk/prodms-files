package com.hydroyura.prodms.files.server.data;

import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MinioClient;
import io.minio.StatObjectArgs;
import io.minio.StatObjectResponse;
import io.minio.errors.ErrorResponseException;
import io.minio.http.Method;
import java.util.Optional;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DrawingsRepository {

    private final MinioClient minioClient;
    private final String bucket;


    public DrawingsRepository(MinioClient minioClient, @Value("${minio.drawings.bucket}") String bucket) {
        this.minioClient = minioClient;
        this.bucket = bucket;
    }


    @SneakyThrows
    private String getDrawingUrlInternal(String object) {
        return minioClient.getPresignedObjectUrl(
            GetPresignedObjectUrlArgs.builder()
                .bucket(bucket)
                .method(Method.GET)
                .object(object)
                .build()
        );
    }

    public Optional<String> getDrawingUrl(String object) {
        return getMetadata(object)
            .map(StatObjectResponse::object)
            .map(this::getDrawingUrlInternal);
    }

    //@SneakyThrows
    public Optional<StatObjectResponse> getMetadata(String object) {
        try {
            return Optional.ofNullable(
                minioClient.statObject(
                    StatObjectArgs.builder()
                        .bucket(bucket)
                        .object(object)
                        .build()
            ));
        } catch (ErrorResponseException e) {
            // TODO: make pretty ex handling
            log.warn("", e);
            return Optional.empty();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }




}
