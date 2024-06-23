package org.sopt.jaksim.global.exception;

import org.sopt.jaksim.global.message.ErrorMessage;

public class InvalidValueException extends BusinessException {
    public InvalidValueException() {
        super(ErrorMessage.BAD_REQUEST);
    }

    public InvalidValueException(ErrorMessage errorMessage) {
        super(errorMessage);
    }
}
