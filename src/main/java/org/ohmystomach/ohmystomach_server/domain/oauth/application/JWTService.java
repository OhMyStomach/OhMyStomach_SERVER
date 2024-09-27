package org.ohmystomach.ohmystomach_server.domain.oauth.application;

import lombok.RequiredArgsConstructor;
import org.ohmystomach.ohmystomach_server.global.common.response.ApiResponse;
import org.ohmystomach.ohmystomach_server.global.common.util.JwtTokenProvider;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JWTService {

    private final JwtTokenProvider jwtTokenProvider;

    // 액세스 토큰 발급
    public ApiResponse<String> encodeToken(String uuid) {
        String token = jwtTokenProvider.createToken(uuid);
        return ApiResponse.ok("성공적으로 토큰을 발급했습니다.", token);
    }

    // 리프레시 토큰 발급
    public ApiResponse<String> createRefreshToken(String uuid) {
        String refreshToken = jwtTokenProvider.createRefreshToken(uuid);
        return ApiResponse.ok("성공적으로 리프레시 토큰을 발급했습니다.", refreshToken);
    }

    // 토큰 유효성 검증
    public boolean validateToken(String jwt) {
        return jwtTokenProvider.validateToken(jwt);
    }

    // 리프레시 토큰 유효성 검증
    public boolean validateRefreshToken(String refreshToken) {
        return jwtTokenProvider.validateToken(refreshToken);
    }

    // 토큰에서 UUID 추출
    public String decodeToken(String jwt) {
        return jwtTokenProvider.getUuid(jwt);
    }
}
