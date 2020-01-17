package com.bassiouny.tafseer.model.api;

import com.google.gson.annotations.SerializedName;

public class BaseResponse<T> {

    private int code;
    private String status;
    @SerializedName("data")
    private T t;

    public int getCode() {
        return code;
    }

    public String getStatus() {
        return status;
    }

    public T getT() {
        return t;
    }
}
