package org.sopt.jaksim.user.api;

import lombok.RequiredArgsConstructor;
import org.sopt.jaksim.auth.PrincipalHandler;
import org.sopt.jaksim.global.common.ApiResponseUtil;
import org.sopt.jaksim.global.common.BaseResponse;
import org.sopt.jaksim.global.message.SuccessMessage;
import org.sopt.jaksim.user.dto.request.UserReissueRequest;
import org.sopt.jaksim.user.dto.request.UserSignInRequest;
import org.sopt.jaksim.user.dto.response.UserSignInResponse;
import org.sopt.jaksim.user.facade.UserFacade;
import org.sopt.jaksim.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static org.sopt.jaksim.global.common.Constants.AUTHORIZATION;
import static org.sopt.jaksim.global.message.SuccessMessage.USER_SIGN_IN_SUCCESS;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class UserApiController {

    private final UserFacade userFacade;

//    @PostMapping("/user/login")
//    public ResponseEntity<BaseResponse<UserSignInResponse>> login(@RequestBody UserSignInRequest userSignInRequest) {
//        final UserSignInResponse response = userFacade.login(userSignInRequest);
//        return ResponseEntity.status(HttpStatus.OK)
//                .body(SuccessStatusResponse.of(SuccessMessage.USER_SIGN_IN_SUCCESS, response));
//    }
//

    @PostMapping("/reissue")
    public ResponseEntity<BaseResponse<?>> reissue(@RequestHeader(AUTHORIZATION) final String refreshToken,
                                                      @RequestBody final UserReissueRequest userReissueRequest) {
        UserSignInResponse response = userFacade.reissue(refreshToken, userReissueRequest);
        return ApiResponseUtil.success(SuccessMessage.USER_TOKEN_REISSUE_SUCCESS, response);
    }

}






