package com.mgnote.mgnote.model.dto;

public class ResponseValue <T>{
    private Integer code;
    private String message;
    private T payload;

    public ResponseValue(Integer code, String message, T payload) {
        this.code = code;
        this.message = message;
        this.payload = payload;
    }

    public ResponseValue() {
    }

    public static <T> ResponseValue<T> success(){
        return new ResponseValue<>(200, "success", null);
    }

    public static <T> ResponseValue<T> success(T payload){
        return new ResponseValue<>(200, "success", payload);
    }

    public static <T> ResponseValue<T> fail(T payload){
        return new ResponseValue<>(300, "fail", payload);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getPayload() {
        return payload;
    }

    public void setPayload(T payload) {
        this.payload = payload;
    }

    @Override
    public String toString() {
        return "ResponseValue{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", payload=" + payload +
                '}';
    }
}
