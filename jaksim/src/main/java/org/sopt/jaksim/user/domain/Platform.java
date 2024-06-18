package org.sopt.jaksim.user.domain;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.sopt.jaksim.global.exception.InvalidValueException;
import org.sopt.jaksim.global.message.ErrorMessage;

import java.util.Arrays;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum Platform {
    GOOGLE("Google");

    private final String stringPlatform;

    public static Platform getEnumPlatformFromStringPlatform(String stringPlatform) {
        return Arrays.stream(values())
                .filter(platform -> platform.stringPlatform.equals(stringPlatform))
                .findFirst()
                .orElseThrow(() -> new InvalidValueException(ErrorMessage.INVALID_PLATFORM_TYPE));
    }
}
