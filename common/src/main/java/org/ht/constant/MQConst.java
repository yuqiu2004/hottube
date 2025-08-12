package org.ht.constant;

public interface MQConst {

    // 视频发布
    String VIDEO_PUBLISH_QUEUE_NAME = "video.publish.queue";
    String VIDEO_PUBLISH_EXCHANGE_NAME = "video.publish.exchange";
    String VIDEO_PUBLISH_ROUTING_KEY = "video.publish.key";

    // 视频转码
    String VIDEO_TRANS_QUEUE_NAME = "video.trans.queue";
    String VIDEO_TRANS_EXCHANGE_NAME = "video.trans.exchange";
    String VIDEO_TRANS_ROUTING_KEY = "video.trans.key";

}
