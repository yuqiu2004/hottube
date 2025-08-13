package org.ht.util;

import org.ht.model.properties.VideoProperties;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class LocalPathUtil {

    @Resource
    private VideoProperties videoProperties;

    public String getVideoBaseDir(int uid, String filename) {
        return videoProperties.getBase() + "/" + uid + "/" + filename;
    }

    public String getChunkDir(int uid, String filename) {
        return getVideoBaseDir(uid, filename) + "/shard";
    }

    public String getOriginalFilePath(int uid, String filename) {
        return getVideoBaseDir(uid, filename) + "/" + filename + ".mp4";
    }

    public String get720pPath(int uid, String filename) {
        return getVideoBaseDir(uid, filename) + "/" + filename + "_720p.mp4";
    }

    public String get360pPath(int uid, String filename) {
        return getVideoBaseDir(uid, filename) + "/" + filename + "_360p.mp4";
    }

    public String getOriM3u8Path(int uid, String filename) {
        return getVideoBaseDir(uid, filename) + "/" + filename + "_ori.m3u8";
    }

    public String getOriTsDir(int uid, String filename) {
        return getVideoBaseDir(uid, filename) + "/ts_ori";
    }

    public String get720pM3u8Path(int uid, String filename) {
        return getVideoBaseDir(uid, filename) + "/" + filename + "_720p.m3u8";
    }

    public String get720pTsDir(int uid, String filename) {
        return getVideoBaseDir(uid, filename) +  "/ts_720p";
    }

    public String get360pM3u8Path(int uid, String filename) {
        return getVideoBaseDir(uid, filename) + "/" + filename + "_360p.m3u8";
    }

    public String get360pTsDir(int uid, String filename) {
        return getVideoBaseDir(uid, filename) + "/ts_360p";
    }

    public String getImgPath(int uid, String filename) {
        return getVideoBaseDir(uid, filename) + "/cover.jpg";
    }
}
