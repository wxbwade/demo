package com.example.demo.model;

public class DemoResponse {

    private Integer code;
    private String message;
    private Object value;

    public DemoResponse(Object value){
        this(DemoResponseCode.CODE_0.getCode(),DemoResponseCode.CODE_0.getMessage(),value);
    }

    public DemoResponse(Integer code, String message, Object value) {
        this.code = code;
        this.message = message;
        this.value = value;
    }

    public Integer getStatus() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Object getValue() {
        return value;
    }
}
