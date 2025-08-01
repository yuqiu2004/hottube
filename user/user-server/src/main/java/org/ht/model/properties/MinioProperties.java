package org.ht.model.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class MinioProperties {

    @Value("minio.endpoint")
    private String endpoint;

    @Value("minio.accessKey")
    private String accessKey;

    @Value("minio.secretKey")
    private String secretKey;

    @Value("minio.bucket")
    private String bucket;

}
