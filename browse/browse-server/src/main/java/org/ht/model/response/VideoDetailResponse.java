package org.ht.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ht.model.vo.UserVo;
import org.ht.model.vo.VideoDetailVo;
import org.ht.model.vo.VideoVo;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VideoDetailResponse {

    private VideoDetailVo videoDetailVo;

    private UserVo userVo;

}
