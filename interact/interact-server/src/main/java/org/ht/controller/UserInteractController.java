package org.ht.controller;

import org.ht.model.response.MessageResponse;
import org.ht.model.response.Resp;
import org.ht.model.response.UserListResponse;
import org.ht.service.ChatMessageService;
import org.ht.service.UserRelationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/interact/user")
public class UserInteractController {

    @Resource
    private UserRelationService userRelationService;

    @Resource
    private ChatMessageService chatMessageService;

    @GetMapping("/followings")
    public Resp<UserListResponse> followings(@RequestParam int page, @RequestParam int pageSize) {
        return Resp.success(userRelationService.followings(page, pageSize));
    }

    @GetMapping("/followers")
    public Resp<UserListResponse> followers(@RequestParam int page, @RequestParam int pageSize) {
        return Resp.success(userRelationService.followers(page, pageSize));
    }

    @GetMapping("/messages")
    public Resp<MessageResponse> getHistoryMessages(@RequestParam Integer fid,
                                                    @RequestParam(defaultValue = "1") int current,
                                                    @RequestParam(defaultValue = "10") int size) {
        return Resp.success(chatMessageService.getHistoryMessages(fid, current, size));
    }

}
