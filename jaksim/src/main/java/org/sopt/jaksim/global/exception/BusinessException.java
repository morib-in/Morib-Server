package org.sopt.jaksim.global.exception;

import lombok.Getter;
import org.sopt.jaksim.global.message.ErrorMessage;

@Getter
public class BusinessException extends RuntimeException {
    public ErrorMessage errorMessage;

    public BusinessException(ErrorMessage errorMessage) {
        super(errorMessage.getMessage());
        this.errorMessage = errorMessage;
    }
}
