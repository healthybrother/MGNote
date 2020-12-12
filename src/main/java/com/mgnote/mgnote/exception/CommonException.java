package com.mgnote.mgnote.exception;

public class CommonException extends RuntimeException{
    private static final long serialVersionUID = 4836827547108110089L;
    private int code;

    public CommonException(final int code, final String message) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(final int code) {
        this.code = code;
    }
}
