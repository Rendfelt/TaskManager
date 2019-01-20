package org.dragard.projectmanager.server.entity;

public class Response {

    private String token;
    private String message;
    private byte[] exception;

    public Response() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public byte[] getException() {
        return exception;
    }

    public void setException(byte[] exception) {
        this.exception = exception;
    }
}
