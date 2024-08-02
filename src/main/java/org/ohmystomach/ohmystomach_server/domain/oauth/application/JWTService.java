//package org.ohmystomach.ohmystomach_server.domain.oauth.application;
//
//import lombok.RequiredArgsConstructor;
//import org.ohmystomach.ohmystomach_server.global.common.response.ApiResponse;
//import org.ohmystomach.ohmystomach_server.global.common.util.JwtTokenProvider;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//public class JWTService {
//
//    private final JwtTokenProvider jwtTokenProvider;
//
//    public ApiResponse<String> encodeToken(String uuid) {
//        String token = jwtTokenProvider.createToken(uuid);
//        return ApiResponse.ok("성공적으로 토큰을 발급했습니다.", token);
//    }
//
//    public boolean validateToken(String jwt) {
//        return jwtTokenProvider.validateToken(jwt);
//    }
//
//    public String decodeToken(String jwt) {
//        return jwtTokenProvider.getUuid(jwt);
//    }
//}
