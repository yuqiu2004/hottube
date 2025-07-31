package org.ht.model.response;

import lombok.Data;

@Data
public class Resp<T> {

    private int code;
    private String msg;
    private T data;

    private Resp(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> Resp<T> success(T data) {
        return new Resp<>(200, "成功", data);
    }

    public static <T> Resp<T> success(String msg, T data) {
        return new Resp<>(200, msg, data);
    }

    public static <T> Resp<T> success() {
        return new Resp<>(200, "成功", null);
    }

    public static <T> Resp<T> fail(int code, String msg) {
        return new Resp<>(code, msg, null);
    }

    public static <T> Resp<T> fail(String msg) {
        return new Resp<>(500, msg, null);
    }

    public static <T> Resp<T> fail() {
        return new Resp<>(500, "失败", null);
    }

    public static <T> Resp<T> unauthorized(String msg) {
        return new Resp<>(401, msg, null);
    }
}
