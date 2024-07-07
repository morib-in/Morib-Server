package org.sopt.jaksim.global.exception;

import org.sopt.jaksim.global.message.ErrorMessage;

public class DateTimeParseException extends BusinessException{
    public DateTimeParseException (ErrorMessage errorMessage) {
        super(errorMessage);
    }
}
