package com.hydroyura.prodms.files.server.data;

import static com.hydroyura.prodms.files.server.data.DrawingRepositoryTestUnits.MINIO_DOCKER_IMAGE;
import static com.hydroyura.prodms.files.server.data.DrawingRepositoryTestUnits.MINIO_DRAWING_BUCKET;
import static com.hydroyura.prodms.files.server.data.DrawingRepositoryTestUnits.MINIO_PWD;
import static com.hydroyura.prodms.files.server.data.DrawingRepositoryTestUnits.MINIO_USER;
import static org.junit.jupiter.api.Assertions.*;

import io.minio.MinioClient;
import java.util.List;
import org.junit.ClassRule;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.MinIOContainer;
import org.testcontainers.utility.DockerImageName;


class DrawingsRepositoryTest {

    private final DrawingsRepository repository;

    @ClassRule
    public static MinIOContainer TEST_CONTAINER =
        new MinIOContainer(MINIO_DOCKER_IMAGE)
            .withUserName(MINIO_USER)
            .withPassword(MINIO_PWD);

    @BeforeAll
    static void startContainer() throws Exception {
        TEST_CONTAINER.start();
        TEST_CONTAINER.execInContainer("bash", "-c", "mc mb minio/%s".formatted(MINIO_DRAWING_BUCKET));
    }

    @AfterAll
    static void closeContainer() {
        TEST_CONTAINER.close();
    }

    DrawingsRepositoryTest() {
        var minioClient = MinioClient.builder()
            .endpoint(TEST_CONTAINER.getS3URL())
            .credentials(TEST_CONTAINER.getUserName(), TEST_CONTAINER.getPassword())
            .build();
        this.repository = new DrawingsRepository(minioClient, MINIO_DRAWING_BUCKET);
    }

    @Test
    void test() {
        int a = 1;
    }


    /*
    cat <<'EOF' > example.txt
Это строка 1
Это строка 2
Это строка 3
EOF
     */



    /*


# Создаем переменную с текстом
TEXT="Это строка 1
Это строка 2
Это строка 3"

# Записываем значение переменной в файл
echo "$TEXT" > example.txt


     */



}