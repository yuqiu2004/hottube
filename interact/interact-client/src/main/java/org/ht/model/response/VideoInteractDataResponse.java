package org.ht.model.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VideoInteractDataResponse {
    private long like;
    private long favorite;
}
