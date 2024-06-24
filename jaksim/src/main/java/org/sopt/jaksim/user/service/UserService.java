package org.sopt.jaksim.user.service;

import lombok.RequiredArgsConstructor;
import org.sopt.jaksim.auth.UserAuthentication;
import org.sopt.jaksim.global.common.jwt.JwtTokenProvider;
import org.sopt.jaksim.global.exception.UnauthorizedException;
import org.sopt.jaksim.global.message.ErrorMessage;
import org.sopt.jaksim.user.domain.User;
import org.sopt.jaksim.user.dto.request.UserSignInRequest;
import org.sopt.jaksim.user.dto.response.UserSignInResponse;
import org.sopt.jaksim.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Member;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final RedisTokenRepository redisTokenRepository;

    @Transactional
    public UserSignInResponse signIn(UserSignInRequest request) {
        // 1. 사용자 자격 증명 검증
        Member member = userRepository.findByUserIdAndPassword(request.getUserId(), request.getPassword())
                .orElseThrow(() -> new RuntimeException("Invalid credentials")); // 클래스명을 userRepository로 수정

        // 2. 토큰 발행
        Long memberId = member.getId();
        String accessToken = jwtTokenProvider.issueAccessToken(
                UserAuthentication.createUserAuthentication(memberId)
        );
        String refreshToken = jwtTokenProvider.issueRefreshToken(
                UserAuthentication.createUserAuthentication(memberId)
        );

        // 3. 리프레시 토큰 저장
        redisTokenRepository.save(Token.of(memberId.toString(), refreshToken));

        // 4. 토큰 반환
        return new UserSignInResponse(accessToken, refreshToken, memberId.toString());
    }

    public UserSignInResponse refreshToken(Long memberId) {
        if (!redisTokenRepository.existsById(memberId.toString())) {
            throw new UnauthorizedException("Invalid refresh token"); // 예외 메시지로 수정
        }

        // 유저 ID 검증 및 토큰 재발급
        User user = userRepository.findById(memberId)
                .orElseThrow(() -> new UnauthorizedException(ErrorMessage.NOT_FOUND));

        String accessToken = jwtTokenProvider.issueAccessToken(
                UserAuthentication.createUserAuthentication(memberId)
        );
        String refreshToken = jwtTokenProvider.issueRefreshToken(
                UserAuthentication.createUserAuthentication(memberId)
        );

        redisTokenRepository.save(Token.of(memberId.toString(), refreshToken)); // 토큰 저장 구문 정리

        return new UserSignInResponse(accessToken, refreshToken, memberId.toString());
    }
}

