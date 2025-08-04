package org.ht.service;

import org.ht.model.response.BrowseRandomResponse;
import org.ht.model.response.Resp;
import org.ht.model.response.VideoDetailResponse;

public interface BrowseService {
    BrowseRandomResponse random();

    void recordWatched(Long videoId);

    VideoDetailResponse detail(Long videoId);
}
