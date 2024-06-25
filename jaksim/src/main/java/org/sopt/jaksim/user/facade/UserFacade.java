package org.sopt.jaksim.user.facade;

import lombok.RequiredArgsConstructor;
import org.sopt.jaksim.user.dto.request.UserReissueRequest;
import org.sopt.jaksim.user.dto.response.UserSignInResponse;
import org.sopt.jaksim.user.service.UserService;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserFacade {
    private final UserService userService;

    public UserSignInResponse reissue(String refreshToken, UserReissueRequest userReissueRequest) {
        return userService.reissue(refreshToken, userReissueRequest);
    }
}
