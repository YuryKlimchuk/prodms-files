package com.hydroyura.prodms.files.server.data;

import com.hydroyura.prodms.files.server.api.enums.DrawingType;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.ListObjectsArgs;
import io.minio.MinioClient;
import io.minio.Result;
import io.minio.StatObjectArgs;
import io.minio.StatObjectResponse;
import io.minio.errors.ErrorResponseException;
import io.minio.http.Method;
import io.minio.messages.Item;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
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


    @SneakyThrows //TODO: handle ex
    private String getDrawingUrlInternal(String object) {
        return minioClient.getPresignedObjectUrl(
            GetPresignedObjectUrlArgs.builder()
                .bucket(bucket)
                .method(Method.GET)
                .object(object)
                .build()
        );
    }


    public Map<DrawingType, String> listObjects(String number) {
        var result = minioClient.listObjects(
            ListObjectsArgs.builder()
                .bucket(bucket)
                .prefix(number)
                .includeUserMetadata(true)
            .build());
        return StreamSupport.stream(result.spliterator(), false)
            .map(this::convertResultItem)
            .collect(Collectors.toMap(Pair::getKey, Pair::getValue));
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


    // TODO: handle ex
    private Pair<DrawingType, String> convertResultItem(Result<Item> item) {
        try {
            Item arg = item.get();
            String objectName = arg.objectName();
            //DrawingType type = convertMetaToType(arg.userMetadata().get("type"));
            DrawingType type = extractTypeFromTags(arg.userTags());
            return new ImmutablePair<>(type, objectName);
        } catch (Exception e) {
            log.error("Some error", e);
            throw new RuntimeException("");
        }
    }

    // TODO: handle ex
    private DrawingType convertMetaToType(String value) {
        return Arrays
            .stream(DrawingType.values())
            .filter(arg -> arg.getCode().equalsIgnoreCase(value))
            .findFirst()
            .orElseThrow(() -> new RuntimeException("Can't !!!!"));
    }

    private DrawingType extractTypeFromTags1(String tags) {
        var type = Arrays.stream(tags.split("&"))
            .map(s -> s.split("="))
            .filter(arr -> arr.length == 2)
            .map(arr -> Map.entry(arr[0], arr[1]))
            .filter(entry -> "type".equals(entry.getKey()))
            .map(Map.Entry::getValue)
            .findFirst()
            .orElseThrow(() -> new RuntimeException("Can't !!!!"));

        return Arrays
            .stream(DrawingType.values())
            .filter(arg -> arg.getCode().equalsIgnoreCase(type))
            .findFirst()
            .orElseThrow(() -> new RuntimeException("Can't !!!!"));
    }


    private DrawingType extractTypeFromTags(String tags) {
        return Optional
            .ofNullable(tags)
            .stream()
            .map(s -> s.split("&"))
            .flatMap(Arrays::stream)
            .map(s -> s.split("="))
            .filter(arr -> arr.length == 2)
            .map(arr -> Map.entry(arr[0], arr[1]))
            .filter(entry -> "type".equals(entry.getKey()))
            .map(Map.Entry::getValue)
            .findFirst()
            .flatMap(this::typeByCode)
            .orElseThrow(() -> new RuntimeException("Need to create custom exception"));
    }

    private Optional<DrawingType> typeByCode(String code) {
        return Arrays
            .stream(DrawingType.values())
            .filter(arg -> arg.getCode().equalsIgnoreCase(code))
            .findFirst();
    }


}
