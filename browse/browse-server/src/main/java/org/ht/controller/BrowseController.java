package org.ht.controller;

import org.ht.model.response.BrowseRandomResponse;
import org.ht.model.response.Resp;
import org.ht.service.BrowseService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/browse")
public class BrowseController {

    @Resource
    private BrowseService browseService;

    @GetMapping("/random")
    public Resp<BrowseRandomResponse> random() {
        return Resp.success(browseService.random());
    }
}
