package org.sopt.jaksim.global.exception;

import org.sopt.jaksim.global.message.ErrorMessage;

public class ForbiddenException extends BusinessException {
    public ForbiddenException(ErrorMessage errorMessage) {
        super(errorMessage);
    }
}
