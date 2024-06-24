package org.sopt.jaksim.user.dto.response;

public record UserSignInResponse(
        String accessToken,
        String refreshToken,
        String userId
) {

    public static UserSignInResponse of(
            String accessToken,
            String refreshToken,
            String userId
    ) {
        return new UserSignInResponse(accessToken, refreshToken, userId);
    }
}