package org.sopt.jaksim.global.exception;

import org.sopt.jaksim.global.message.ErrorMessage;

public class UnauthorizedException extends BusinessException {
    public UnauthorizedException(ErrorMessage errorMessage) {
        super(errorMessage);
    }
}
