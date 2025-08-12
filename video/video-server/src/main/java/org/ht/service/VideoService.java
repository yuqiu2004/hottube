package org.ht.service;

import org.ht.model.dto.VideoMetadataDTO;
import org.ht.model.response.InitUploadResponse;
import org.ht.model.response.UploadChunkResponse;
import org.ht.model.response.UploadMetadataResponse;
import org.springframework.web.multipart.MultipartFile;

public interface VideoService {
    InitUploadResponse initUpload(String filename, double size);

    UploadChunkResponse uploadChunk(MultipartFile file, String filename, int chunkIndex, int totalChunks);

    boolean mergeChunks(String filename, int totalChunks);

    UploadMetadataResponse uploadMetadata(String filename, VideoMetadataDTO metadataDTO);
}
