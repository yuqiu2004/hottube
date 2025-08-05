package org.ht.service;

import org.ht.model.response.UserListResponse;

/**
 * 用户关系Service接口
 * 
 * @author HotTube
 * @since 2024-01-01
 */
public interface UserRelationService {

    UserListResponse followings(int page, int pageSize);

    UserListResponse followers(int page, int pageSize);
}
