package org.ht.controller;

import org.ht.model.dto.VideoMetadataDTO;
import org.ht.model.response.*;
import org.ht.service.VideoService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@RestController
@RequestMapping("/video")
public class VideoController {

    @Resource
    private VideoService videoService;

    @GetMapping("/upload/init/{size}")
    public Resp<InitUploadResponse> initUpload(@RequestParam String filename, @PathVariable double size) {
        return Resp.success(videoService.initUpload(filename, size));
    }

    @PostMapping(value = "/upload-chunk")
    public Resp<UploadChunkResponse> uploadChunk(
            @RequestParam("file") MultipartFile file,
            @RequestParam("filename") String filename,
            @RequestParam("chunkIndex") int chunkIndex,
            @RequestParam("totalChunks") int totalChunks) {
        return Resp.success(videoService.uploadChunk(file, filename, chunkIndex, totalChunks));
    }

    @GetMapping("/merge-chunks")
    public Resp<Void> mergeChunks(
            @RequestParam("filename") String filename,
            @RequestParam("totalChunks") int totalChunks) {
        return videoService.mergeChunks(filename, totalChunks) ? Resp.success() : Resp.fail();
    }

    @PostMapping("/metadata")
    public Resp<UploadMetadataResponse> uploadMetadata(@RequestParam String filename,
                                                       @RequestBody VideoMetadataDTO metadataDTO) {
        return Resp.success(videoService.uploadMetadata(filename, metadataDTO));
    }

}
