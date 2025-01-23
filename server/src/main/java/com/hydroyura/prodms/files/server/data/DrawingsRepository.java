package com.hydroyura.prodms.files.server.data;

import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MinioClient;
import io.minio.http.Method;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DrawingsRepository {

    private final MinioClient minioClient;
    private final String bucket;

    @SneakyThrows
    public Optional<String> getDrawingUrl(String number) {
        return Optional.ofNullable(
            minioClient.getPresignedObjectUrl(
                GetPresignedObjectUrlArgs.builder()
                    .bucket(bucket)
                    .method(Method.GET)
                    .object(number)
                    .build()
            )
        );
    }




}
