package org.ht.service;

import org.ht.model.dto.VideoMetadataDTO;
import org.ht.model.response.VideoPublishResponse;

public interface VideoPublishRpcService {

    VideoPublishResponse publish(VideoMetadataDTO videoMetadataDTO);

}
