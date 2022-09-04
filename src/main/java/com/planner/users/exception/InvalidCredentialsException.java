package com.planner.users.exception;

import com.planner.users.dto.server.ServerErrorResponseDto;
import com.planner.users.utils.ErrorCodeEnum;
import org.springframework.http.HttpStatus;

import javax.ws.rs.InternalServerErrorException;

public class InvalidCredentialsException extends InternalServerErrorException {
    private ServerErrorResponseDto serverErrorResponseDto;

    public InvalidCredentialsException() {
        super();
        serverErrorResponseDto = new ServerErrorResponseDto(HttpStatus.NOT_FOUND, "User not found", ErrorCodeEnum.USER_NOT_FOUND);
    }
}
