package com.smbms.exception;

/**
 * 自定义异常
 */
public class UserException extends Exception{
    private String message;

    public UserException(String message) {
        super(message);
        this.message = message;
    }

    public UserException() {
      super();
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
