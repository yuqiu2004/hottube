package org.ht;

import org.ht.util.MinioUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class UploadTest {

    @Resource
    private MinioUtil minioUtil;

    @Test
    public void testUploadFile() {
        String url = minioUtil.uploadFile("C:/Users/SN/Pictures/视频项目/executionResult.mp4", "test/executionResult.mp4");
        System.out.println(url);
    }
}
