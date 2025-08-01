package org.ht.model.context;

public class ContextData {

    private static ThreadLocal<Integer> userId = new ThreadLocal<>();

    public static void setUserId(int uid) {
        userId.set(uid);
    }

    public static int getUserId() {
        return userId.get();
    }

    public static void clearUserId() {
        userId.remove();
    }

}
