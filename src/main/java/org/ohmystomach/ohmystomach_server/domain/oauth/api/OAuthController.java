package org.ohmystomach.ohmystomach_server.domain.oauth.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.ohmystomach.ohmystomach_server.domain.oauth.application.JWTService;
import org.ohmystomach.ohmystomach_server.domain.oauth.application.KakaoService;
import org.ohmystomach.ohmystomach_server.domain.user.application.UserService;
import org.ohmystomach.ohmystomach_server.domain.user.dao.UserRepository;
//import org.ohmystomach.ohmystomach_server.global.common.util.JwtTokenProvider;
import org.ohmystomach.ohmystomach_server.domain.user.domain.User;
import org.ohmystomach.ohmystomach_server.global.common.response.ApiResponse;
import org.ohmystomach.ohmystomach_server.global.error.ErrorCode;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Tag(name = "회원가입/로그인 API", description = "회원가입, 로그인, 사용자 정보 조회 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/oauth")
//@Transactional(readOnly = true)
public class OAuthController {
////  private final JwtTokenProvider jwtTokenProvider;
  private final KakaoService kakaoService;
  private final UserRepository userRepository;
  private final UserService userService;
  private final JWTService jwtService;
////
////  public OAuthController(KakaoService kakaoService, UserRepository userRepository, JwtTokenProvider jwtTokenProvider) {
////    this.kakaoService = kakaoService;
//////    this.userService = userService;
////    this.jwtTokenProvider = jwtTokenProvider;
////    this.userRepository = userRepository;
//////    this.jwtService = jwtService;
////  }
//

  @Operation(summary="리다이렉션 url 반환 API")
  @GetMapping("/login")
  public ApiResponse<String> getKakaoLoginUrl() {
    return kakaoService.getKakaoLoginUrl();
  }

  @Operation(summary="로그인 후 콜백 메소드")
  @GetMapping("/callback")
  public ApiResponse<String> kakaoLogin(@RequestParam("code") String code) {
    System.out.println("0000000000000000000000000");
    String accessToken = kakaoService.getAccessToken(code);
    Map<String, Object> userInfo = kakaoService.getUserInfo(accessToken);

//    String uuid = userInfo.get("id").toString();
//    String email = (String) ((Map<String, Object>) userInfo.get("kakao_account")).get("email");
//    String name = (String) ((Map<String, Object>) userInfo.get("properties")).get("nickname");

////    User user = userRepository.findById(uuid).orElseGet(() -> {
////      User newUser = new User();
////      newUser.setId(uuid);
////      newUser.setEmail(email);
////      newUser.setNickname(name);
////      return userRepository.save(newUser);
////    });
//////
//    return userService.retrieveOrCreateUser(userInfo);
//////    return ApiResponse.ok("성공", jwtTokenProvider.createToken(user.getId()));
////////    return jwtTokenProvider.createToken(userService.retrieveOrCreateUser(userInfo).getData().getId());
    return jwtService.encodeToken(userService.retrieveOrCreateUser(userInfo).getData().getId());
  }
//
  @Operation(summary="유저 정보 조회 API", description = "JWT 토큰으로 유저 정보 조회 API")
  @GetMapping("/user")
  public ApiResponse<User> getUserInfo(@RequestHeader("Authorization") String token) {
    String jwt = token.substring(7); // "Bearer " 제거
    if(!jwtService.validateToken(jwt)) {
      return ApiResponse.withError(ErrorCode.UNAUTHORIZED_ERROR);
    }

    String userId = jwtService.decodeToken(jwt);
    ApiResponse<User> response = userService.retrieveUser(userId);
    User user = response.getData();

    // 유효한 AccessToken 확보
    String accessToken = userService.getValidAccessToken(user);

    // 필요 시 accessToken을 사용하여 추가 작업 수행
    return response;
  }
//  @Operation(summary="유저 정보 조회 API", description = "JWT 토큰으로 유저 정보 조회 API")
//  @GetMapping("/user")
//  public ApiResponse<User> getUserInfo(@RequestHeader("Authorization") String token) {
//    String jwt = token.substring(7); // "Bearer " 제거
//    if(!jwtService.validateToken(jwt)) {
//      return ApiResponse.withError(ErrorCode.UNAUTHORIZED_ERROR);
//    }
//    return userService.retrieveUser(jwtService.decodeToken(jwt));
//    if (jwtTokenProvider.validateToken(jwt)) {
//      String uuid = jwtTokenProvider.getUuid(jwt);
//      User user = userRepository.findById(uuid).orElseThrow(() -> new UsernameNotFoundException("User not found"));
//      return ApiResponse.ok("성공했다 이거야", user);
//    } else {
//      return ApiResponse.withError(ErrorCode.UNAUTHORIZED_ERROR);
//    }
//  }

  @Operation(summary="로그아웃 API", description = "카카오 로그아웃 API")
  @GetMapping("logout")
  public ApiResponse<Boolean> kakaoLogout(@RequestHeader("Authorization") String token) {
    if (!jwtService.validateToken(token)) {
      return ApiResponse.withError(ErrorCode.UNAUTHORIZED_ERROR);
    }
    // JWT에서 사용자 ID 추출
    String userId = jwtService.decodeToken(token);

    // 사용자 정보에서 카카오 액세스 토큰 가져오기
    User user = userService.retrieveUser(userId).getData();
    String accessToken = user.getKakaoAccessToken();

    // 1. 카카오 로그아웃 요청
    ApiResponse<Boolean> response = kakaoService.logout(accessToken);
    boolean kakaoLogoutSuccess = response.getData();
    if (!kakaoLogoutSuccess) {
      return ApiResponse.withError(ErrorCode.KAKAO_LOGOUT_FAILED);
    }

    // 2. 서버 측에서 세션 무효화 또는 상태 관리
//    return userService.invalidateUserSession(userId);

//    return ApiResponse.ok();
    return response;
  }
////
//////  @Value("${kakao.auth-url}")
//////  private String authUrl;
//////
//////  @Value("${kakao.client-id}")
//////  private String clientId;
//////
//////  @Value("${kakao.redirect-uri}")
//////  private String redirectUri;
//////
//////  private final OAuthService oAuthService;
//////
//////  @GetMapping("/login")
//////  public ApiResponse<String> login() {
//////    String kakaoAuthUrl = String.format("%s?client_id=%s&redirect_uri=%s&response_type=code",
//////            authUrl, clientId, redirectUri);
//////    return ApiResponse.ok("Redirect to Kakao", kakaoAuthUrl);
//////  }
//////
////////  @GetMapping("/callback")
////////  public Mono<ApiResponse<Map<String, Object>>> callback(@RequestParam String code) {
////////    return oAuthService.getAccessToken(code);
////////  }
//////
//////  @GetMapping("/callback")
//////  public Mono<ApiResponse<Map<String, Object>>> callback(@RequestParam String code) {
//////    return oAuthService.getUserInfoFromCode(code);
//////  }
//////
////////  @GetMapping("/userinfo")
////////  public Mono<ApiResponse<Map<String, Object>>> userinfo(@RequestParam String accessToken) {
////////    return oAuthService.getUserInfo(accessToken);
////////  }
//////
////////  @GetMapping("/userinfo")
////////  public Mono<ApiResponse<Map<String, Object>>> userinfo() {
////////    return oAuthService.getCurrentUserInfo();
////////  }
//  @Operation(summary="로그아웃 API", description = "카카오 로그아웃 API")
//  @PostMapping("/logout")
//  public ApiResponse<User> logout(@RequestHeader("Authorization") String token) {
//    String jwt = token.substring(7);
//    if (!jwtService.validateToken(jwt)) {
//      return ApiResponse.withError(ErrorCode.UNAUTHORIZED_ERROR);
//    }
//
//    // JWT에서 사용자 ID 추출
//    String userId = jwtService.decodeToken(jwt);
//
//    // 사용자 정보에서 카카오 액세스 토큰 가져오기
//    User user = userService.retrieveUser(userId).getData();
//    String accessToken = user.getKakaoAccessToken();
//
//    // 1. 카카오 로그아웃 요청
//    boolean kakaoLogoutSuccess = kakaoService.logout(accessToken);
//    if (!kakaoLogoutSuccess) {
//      return ApiResponse.withError(ErrorCode.KAKAO_LOGOUT_FAILED);
//    }
//
//    // 2. 서버 측에서 세션 무효화 또는 상태 관리
//    return userService.invalidateUserSession(userId);
//
////    return ApiResponse.ok();
//  }
}