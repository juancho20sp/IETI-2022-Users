package com.planner.users.dto.server;

import com.planner.users.utils.ErrorCodeEnum;
import org.springframework.http.HttpStatus;

public class ServerErrorResponseDto {
    int httpStatus;
    String message;
    ErrorCodeEnum errorCodeEnum;

    public ServerErrorResponseDto(HttpStatus httpStatus, String message, ErrorCodeEnum errorCodeEnum) {
        this.httpStatus = httpStatus.value();
        this.message = message;
        this.errorCodeEnum = errorCodeEnum;
    }

    public int getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(int httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ErrorCodeEnum getErrorCodeEnum() {
        return errorCodeEnum;
    }

    public void setErrorCodeEnum(ErrorCodeEnum errorCodeEnum) {
        this.errorCodeEnum = errorCodeEnum;
    }
}
