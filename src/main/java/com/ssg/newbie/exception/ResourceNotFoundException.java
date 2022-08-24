package com.ssg.newbie.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException() {
        super("리소스를 찾을 수 없습니다.");
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
