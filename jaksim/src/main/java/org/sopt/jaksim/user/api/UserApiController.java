package org.sopt.jaksim.user.api;

import lombok.RequiredArgsConstructor;
import org.sopt.jaksim.auth.PrincipalHandler;
import org.sopt.jaksim.global.message.SuccessMessage;
import org.sopt.jaksim.user.dto.request.UserSignInRequest;
import org.sopt.jaksim.user.dto.response.UserSignInResponse;
import org.sopt.jaksim.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.sopt.jaksim.global.message.SuccessMessage.USER_SIGN_IN_SUCCESS;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class UserApiController {

    private final UserService userService;
    private final PrincipalHandler principalHandler;

    @PostMapping("/user/login")
    public ResponseEntity<SuccessStatusResponse<UserSignInResponse>> login(@RequestBody UserSignInRequest userSignInRequest) {
        UserSignInResponse response = userService.login(userSignInRequest);
        return ResponseEntity.status(HttpStatus.OK)
                .body(SuccessStatusResponse.of(SuccessMessage.USER_SIGN_IN_SUCCESS, response));
    }
//ApiResponseUtil<BaseResponse<UserSignInResponse>>>
    // public ApiResponseUtil<BaseResponse ....
    @PostMapping("/refresh")
    public ResponseEntity<UserSignInResponse> refreshToken() {
        Long userId = principalHandler.getUserIdFromPrincipal();
        UserSignInResponse response = userService.refreshToken(userId);
        return ResponseEntity.status(HttpStatus.CREATED)
                .header("Location", response.userId())
                .body(response);
    }
}



}


