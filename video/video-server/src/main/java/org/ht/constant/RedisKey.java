package org.ht.constant;

public class RedisKey {

    private static final String videoUploadStatus = "video:upload:status";

    public static String getUploadStatusKey(Integer uid, String filename) {
        return videoUploadStatus + ":" + uid + ":" + filename;
    }
}
