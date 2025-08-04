package org.ht.model.context;

import org.ht.model.dto.UserInfo;

public class ContextData {

    private static ThreadLocal<UserInfo> userInfo = new ThreadLocal<>();

    public static void setUserInfo(UserInfo info) {
        userInfo.set(info);
    }

    public static UserInfo getUserInfo() {
        return userInfo.get();
    }

    public static void clearUserInfo() {
        userInfo.remove();
    }

}
