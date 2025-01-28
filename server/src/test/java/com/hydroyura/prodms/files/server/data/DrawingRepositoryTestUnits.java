package com.hydroyura.prodms.files.server.data;

import lombok.experimental.UtilityClass;

@UtilityClass
public class DrawingRepositoryTestUnits {

    public static final String MINIO_DOCKER_IMAGE = "minio/minio:RELEASE.2023-09-04T19-57-37Z";
    public static final String MINIO_USER = "minio";
    public static final String MINIO_PWD = "minio123";
    public static final String MINIO_DRAWING_BUCKET = "drawing-test";
    public static final String MINIO_TEST_CONTAINER_NAME = "minio-test";
    public static final String MINIO_TEST_FILE_NAME_1 = "test1.txt";
    public static final String MINIO_TEST_FILE_NAME_2 = "test2.txt";

    public static final String MINIO_TEST_FILE_CONTENT_1 = "This is content number 1";
    public static final String MINIO_TEST_FILE_CONTENT_2 = "This is content number 2";
    public static final String MINIO_TEST_FILE_CONTENT_3 = "This is content number 3";
    public static final String MINIO_TEST_FILE_CONTENT_4 = "This is content number 4";

    public static final String MINIO_CMD_CREATE_FILE = """
cat <<'EOF' >> %s
%s
EOF""";

    public static final String MINIO_CMD_PLACE_FILE_TO_BUCKET = "mc cp %s minio-test/%s/%s";

}