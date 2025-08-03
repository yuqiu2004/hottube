package org.ht.constant;

import org.springframework.stereotype.Component;

@Component
public class RedisKey {

    private static String VIDEO_REC = "video:recommend";

    public static String getVideoRecommendedKey(long uid) {
        return VIDEO_REC + ":" + uid;
    }
}
