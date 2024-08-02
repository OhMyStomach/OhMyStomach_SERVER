package org.ohmystomach.ohmystomach_server.domain.oauth.api;

import lombok.RequiredArgsConstructor;
import org.ohmystomach.ohmystomach_server.domain.oauth.application.JWTService;
import org.ohmystomach.ohmystomach_server.domain.oauth.application.KakaoService;
import org.ohmystomach.ohmystomach_server.domain.user.application.UserService;
import org.ohmystomach.ohmystomach_server.domain.user.dao.UserRepository;
import org.ohmystomach.ohmystomach_server.domain.user.domain.User;
import org.ohmystomach.ohmystomach_server.global.common.response.ApiResponse;
import org.ohmystomach.ohmystomach_server.global.common.util.JwtTokenProvider;
import org.ohmystomach.ohmystomach_server.global.error.ErrorCode;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/oauth")
public class OAuthController {


  private final KakaoService kakaoService;
  private final JwtTokenProvider jwtTokenProvider;
//  private final UserRepository userRepository;
  private final UserService userService;
  private final JWTService jwtService;

  public AuthController(KakaoService kakaoService, JwtTokenProvider jwtTokenProvider, JWTService jwtService) {
    this.kakaoService = kakaoService;
    this.jwtTokenProvider = jwtTokenProvider;
//    this.userRepository = userRepository;
    this.jwtService = jwtService;
  }

  @GetMapping("/login/kakao")
  public String kakaoLogin(@RequestParam String code) {
    String accessToken = kakaoService.getAccessToken(code);
    Map<String, Object> userInfo = kakaoService.getUserInfo(accessToken);

//    String uuid = userInfo.get("id").toString();
//    String email = (String) ((Map<String, Object>) userInfo.get("kakao_account")).get("email");
//    String name = (String) ((Map<String, Object>) userInfo.get("properties")).get("nickname");
//
//    User user = userRepository.findById(uuid).orElseGet(() -> {
//      User newUser = new User();
//      newUser.setId(uuid);
//      newUser.setEmail(email);
//      newUser.setNickname(name);
//      return userRepository.save(newUser);
//    });

//    return jwtTokenProvider.createToken(user.getId());
    return jwtTokenProvider.createToken(userService.retrieveOrCreateUser(userInfo).getData().getId());
  }

  @GetMapping("/user")
  public ApiResponse<User> getUserInfo(@RequestHeader("Authorization") String token) {
    String jwt = token.substring(7); // "Bearer " 제거
    if (!jwtTokenProvider.validateToken(jwt)) {
      return ApiResponse.withError(ErrorCode.UNAUTHORIZED_ERROR);
    }
    String uuid = jwtTokenProvider.getUuid(jwt);
    return userService.retrieveUser(uuid);
  }

//  @Value("${kakao.auth-url}")
//  private String authUrl;
//
//  @Value("${kakao.client-id}")
//  private String clientId;
//
//  @Value("${kakao.redirect-uri}")
//  private String redirectUri;
//
//  private final OAuthService oAuthService;
//
//  @GetMapping("/login")
//  public ApiResponse<String> login() {
//    String kakaoAuthUrl = String.format("%s?client_id=%s&redirect_uri=%s&response_type=code",
//            authUrl, clientId, redirectUri);
//    return ApiResponse.ok("Redirect to Kakao", kakaoAuthUrl);
//  }
//
////  @GetMapping("/callback")
////  public Mono<ApiResponse<Map<String, Object>>> callback(@RequestParam String code) {
////    return oAuthService.getAccessToken(code);
////  }
//
//  @GetMapping("/callback")
//  public Mono<ApiResponse<Map<String, Object>>> callback(@RequestParam String code) {
//    return oAuthService.getUserInfoFromCode(code);
//  }
//
////  @GetMapping("/userinfo")
////  public Mono<ApiResponse<Map<String, Object>>> userinfo(@RequestParam String accessToken) {
////    return oAuthService.getUserInfo(accessToken);
////  }
//
////  @GetMapping("/userinfo")
////  public Mono<ApiResponse<Map<String, Object>>> userinfo() {
////    return oAuthService.getCurrentUserInfo();
////  }
}