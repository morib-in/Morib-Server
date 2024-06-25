package org.sopt.jaksim.user.service;

import lombok.RequiredArgsConstructor;
import org.sopt.jaksim.auth.UserAuthentication;
import org.sopt.jaksim.auth.jwt.JwtTokenProvider;
import org.sopt.jaksim.global.exception.NotFoundException;

import org.sopt.jaksim.global.message.ErrorMessage;
import org.sopt.jaksim.user.domain.Platform;
import org.sopt.jaksim.user.domain.RefreshToken;
import org.sopt.jaksim.user.domain.User;
import org.sopt.jaksim.user.dto.request.UserReissueRequest;
import org.sopt.jaksim.user.dto.request.UserSignInRequest;
import org.sopt.jaksim.user.dto.response.UserSignInResponse;
import org.sopt.jaksim.user.repository.RedisTokenRepository;
import org.sopt.jaksim.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.sopt.jaksim.user.domain.Platform.getEnumPlatformFromStringPlatform;
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final RedisTokenRepository redisTokenRepository;

    @Transactional
    public UserSignInResponse signIn(String token, UserSignInRequest request) {
        Platform platform = getEnumPlatformFromStringPlatform(request.platform());
        // 플랫폼을 판단한 뒤, 토큰을 넣어서 유저 이메일 추출
        String userEmail = getUserEmail(token, platform);

        // 유저 이메일과 플랫폼으로 유저 찾기
        User user = getUserByPlatformAndEmail(platform, userEmail);

        // atk, rtk 발급
        String accessToken = jwtTokenProvider.issueAccessToken(UserAuthentication.createUserAuthentication(user.getId()));
        String refreshToken = jwtTokenProvider.issueRefreshToken(UserAuthentication.createUserAuthentication(user.getId()));

        // redis에 rtk 저장
        redisTokenRepository.save(RefreshToken.of(user.getId(), refreshToken));

        return UserSignInResponse.of(accessToken, refreshToken, user.getId().toString());
    }

    public UserSignInResponse reissue(String token, UserReissueRequest userReissueRequest) {
       Long userId = userReissueRequest.userId();
       validateRefreshToken(token, userId);
       User user = getUser(userId);

       String accessToken = jwtTokenProvider.issueAccessToken(UserAuthentication.createUserAuthentication(userId));
       String refreshToken = jwtTokenProvider.issueRefreshToken(UserAuthentication.createUserAuthentication(userId));

       updateRefreshToken(refreshToken, user);

        return new UserSignInResponse(accessToken, refreshToken, userId.toString());
    }

    private User getUser(Long userId) {
        return userRepository.findById(userId).orElseThrow(
                () -> new NotFoundException(ErrorMessage.NOT_FOUND)
        );
    }

    private User getUserByPlatformAndEmail(Platform platform, String userEmail) {
        return userRepository.findUserByPlatformAndEmail(platform, userEmail).orElseThrow(
            () -> new NotFoundException(ErrorMessage.NOT_FOUND));
    }

    public String getUserEmail(String token, Platform platform) {
        // 추후에 다른 소셜로그인 플랫폼이 있을 수도 있으므로 메소드로 분리함
        // 구글 OAuth 서버에 통신해서 이메일 가져오기
        return null;
    }

    private void validateRefreshToken(String refreshToken, Long userId) {
        // jwt 클래스 따로 만들어서 할 예정
    }

    private void updateRefreshToken(String refreshToken, User user) {
        user.setRefreshToken(refreshToken);
        redisTokenRepository.save(RefreshToken.of(user.getId(), refreshToken));
    }
}

