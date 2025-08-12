package org.ht.model.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "video")
@Component
@Data
public class VideoProperties {

    private String base;
    private int chunkSize;

}
