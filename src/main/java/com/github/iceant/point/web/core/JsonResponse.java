package com.github.iceant.point.web.core;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class JsonResponse implements Serializable {
    private String message;
    private int statusCode;
    private String status;
    private Object data;
    private long timestamp;
    private Map<String, Object> meta = new HashMap<>();

    public Map<String, Object> getMeta() {
        return meta;
    }

    public JsonResponse put(String name, Object value) {
        meta.put(name, value);
        return this;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public JsonResponse setTimestamp(long timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public JsonResponse setStatus(String status) {
        this.status = status;
        return this;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public JsonResponse setStatusCode(int statusCode) {
        this.statusCode = statusCode;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public JsonResponse setMessage(String message) {
        this.message = message;
        return this;
    }

    public Object getData() {
        return data;
    }

    public JsonResponse setData(Object data) {
        this.data = data;
        return this;
    }

    @Override
    public String toString() {
        return "JsonResponse{" +
                "message='" + message + '\'' +
                ", statusCode=" + statusCode +
                ", status='" + status + '\'' +
                ", data=" + data +
                '}';
    }
}
