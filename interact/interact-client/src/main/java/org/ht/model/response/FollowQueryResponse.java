package org.ht.model.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FollowQueryResponse {
    private long followings;
    private long followers;
}
