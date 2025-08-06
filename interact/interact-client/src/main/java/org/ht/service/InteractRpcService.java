package org.ht.service;

import org.ht.model.response.FollowQueryResponse;
import org.ht.model.response.VideoInteractDataResponse;

public interface InteractRpcService {

    FollowQueryResponse flowQuery(int uid);
    VideoInteractDataResponse videoInteractData(int videoId);

}
