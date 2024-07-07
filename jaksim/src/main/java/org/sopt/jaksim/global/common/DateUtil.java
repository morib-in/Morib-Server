package org.sopt.jaksim.global.common;

import org.sopt.jaksim.global.exception.DateTimeParseException;
import org.sopt.jaksim.global.message.ErrorMessage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class DateUtil {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static LocalDate stringToDate(String dateString) {
        try {
            return LocalDate.parse(dateString, DATE_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new DateTimeParseException(ErrorMessage.INVALID_DATE_FORMAT);
        }
    }
}
