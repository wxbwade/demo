package com.example.demo.model;

public enum DemoResponseCode {
    CODE_0(0,"正常响应"),
    CODE_112(112,"参数错误"),
    CODE_114(114,"未找到数据"),
    CODE_999(999,"未知错误");

    private Integer code;
    private String message;

    DemoResponseCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
