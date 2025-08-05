package org.ht.controller;

import org.ht.model.entity.UserVideoComment;
import org.ht.model.response.CommentResponse;
import org.ht.model.response.Resp;
import org.ht.service.UserVideoCommentService;
import org.ht.service.UserVideoFavoriteService;
import org.ht.service.UserVideoLikeService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/interact/video")
public class VideoInteractController {

    @Resource
    private UserVideoLikeService userVideoLikeService;

    @Resource
    private UserVideoCommentService userVideoCommentService;

    @Resource
    private UserVideoFavoriteService userVideoFavoriteService;

    @PostMapping("/{videoId}/like")
    public Resp<Void> likeVideo(@PathVariable Long videoId) {
        userVideoLikeService.likeVideo(videoId);
        return Resp.success();
    }

    @PostMapping("/{videoId}/unlike")
    public Resp<Void> unlikeVideo(@PathVariable Long videoId) {
        userVideoLikeService.unlikeVideo(videoId);
        return Resp.success();
    }

    @PostMapping("/{videoId}/favorite")
    public Resp<Void> favoriteVideo(@PathVariable Long videoId) {
        userVideoFavoriteService.favoriteVideo(videoId);
        return Resp.success();
    }

    @PostMapping("/{videoId}/unfavorite")
    public Resp<Void> unfavoriteVideo(@PathVariable Long videoId) {
        userVideoFavoriteService.unfavoriteVideo(videoId);
        return Resp.success();
    }

    @GetMapping("/{videoId}/comments")
    public Resp<CommentResponse> getComments(@PathVariable Long videoId, @RequestParam int current, @RequestParam int size) {
        return Resp.success(userVideoCommentService.getComments(videoId, current, size));
    }

    @PostMapping("/comment")
    public Resp<Void> addComment(@RequestBody UserVideoComment comment) {
        userVideoCommentService.addComment(comment);
        return Resp.success();
    }

}
