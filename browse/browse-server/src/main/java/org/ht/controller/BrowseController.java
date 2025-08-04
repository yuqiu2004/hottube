package org.ht.controller;

import org.ht.model.response.BrowseRandomResponse;
import org.ht.model.response.CategoryQueryResponse;
import org.ht.model.response.Resp;
import org.ht.model.response.VideoDetailResponse;
import org.ht.service.BrowseService;
import org.ht.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/browse")
public class BrowseController {

    @Resource
    private BrowseService browseService;

    @Resource
    private CategoryService categoryService;

    @GetMapping("/random")
    public Resp<BrowseRandomResponse> random() {
        return Resp.success(browseService.random());
    }

    @GetMapping("/category")
    public Resp<CategoryQueryResponse> category() {
        return Resp.success(categoryService.getAllCategories());
    }

    @GetMapping("/record/watched/{videoId}")
    public Resp<Void> recordWatched(@PathVariable Long videoId) {
        browseService.recordWatched(videoId);
        return Resp.success();
    }

    @GetMapping("/video/{videoId}")
    public Resp<VideoDetailResponse> detail(@PathVariable Long videoId) {
        return Resp.success(browseService.detail(videoId));
    }
}
