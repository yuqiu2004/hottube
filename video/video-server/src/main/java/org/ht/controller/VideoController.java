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

    /**
     * 1. 初始化 需要根据文件的唯一标识创建 需要用户id+文件名
     *      创建目录 形如 /video/id/filename/chunks
     * 2. 上传分片 到对应的目录 需要当前的chunk号 以及文件名
     * 3. 合并 将视频合并为完整的 到 /video/id/filename下 完整路径记录下来 参数传递
     * 4. 转码 等待上传元数据的时候 开始执行 需要抓取封面、画质转化、hls生成
     *
     */

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
