package org.ht.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InitUploadResponse {
    private boolean initSuccess;
    private boolean resumeTrans;
    private int chunkSize;
    private List<Integer> toSendTrunks;
}
