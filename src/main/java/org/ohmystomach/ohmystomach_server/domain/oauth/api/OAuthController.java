//package org.ohmystomach.ohmystomach_server.domain.oauth.api;
//
//import lombok.RequiredArgsConstructor;
//import org.ohmystomach.ohmystomach_server.domain.oauth.application.JWTService;
//import org.ohmystomach.ohmystomach_server.domain.oauth.application.KakaoService;
//import org.ohmystomach.ohmystomach_server.domain.user.application.UserService;
//import org.ohmystomach.ohmystomach_server.domain.user.dao.UserRepository;
//import org.ohmystomach.ohmystomach_server.domain.user.domain.User;
//import org.ohmystomach.ohmystomach_server.global.common.response.ApiResponse;
//import org.ohmystomach.ohmystomach_server.global.common.util.JwtTokenProvider;
//import org.ohmystomach.ohmystomach_server.global.error.ErrorCode;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.Map;
//
//@RestController
////@RequiredArgsConstructor
//@RequestMapping("/oauth")
//public class OAuthController {
////  private final JwtTokenProvider jwtTokenProvider;
////  private final KakaoService kakaoService;
////  private final UserRepository userRepository;
//////  private final UserService userService;
//////  private final JWTService jwtService;
////
////  public OAuthController(KakaoService kakaoService, UserRepository userRepository, JwtTokenProvider jwtTokenProvider) {
////    this.kakaoService = kakaoService;
//////    this.userService = userService;
////    this.jwtTokenProvider = jwtTokenProvider;
////    this.userRepository = userRepository;
//////    this.jwtService = jwtService;
////  }
//
////  @GetMapping("/login/kakao")
////  public ApiResponse<String> kakaoLogin(@RequestParam String code) {
////    String accessToken = kakaoService.getAccessToken(code);
////    Map<String, Object> userInfo = kakaoService.getUserInfo(accessToken);
////
////    String uuid = userInfo.get("id").toString();
////    String email = (String) ((Map<String, Object>) userInfo.get("kakao_account")).get("email");
////    String name = (String) ((Map<String, Object>) userInfo.get("properties")).get("nickname");
////
////    User user = userRepository.findById(uuid).orElseGet(() -> {
////      User newUser = new User();
////      newUser.setId(uuid);
////      newUser.setEmail(email);
////      newUser.setNickname(name);
////      return userRepository.save(newUser);
////    });
////
////    return ApiResponse.ok("성공", jwtTokenProvider.createToken(user.getId()));
//////    return jwtTokenProvider.createToken(userService.retrieveOrCreateUser(userInfo).getData().getId());
//////    return jwtService.encodeToken(userService.retrieveOrCreateUser(userInfo).getData().getId());
////  }
////
////  @GetMapping("/user")
////  public ApiResponse<User> getUserInfo(@RequestHeader("Authorization") String token) {
////    String jwt = token.substring(7); // "Bearer " 제거
//////    if(!jwtService.validateToken(jwt)) {
//////      return ApiResponse.withError(ErrorCode.UNAUTHORIZED_ERROR);
//////    }
//////    return userService.retrieveUser(jwtService.decodeToken(jwt));
////    if (jwtTokenProvider.validateToken(jwt)) {
////      String uuid = jwtTokenProvider.getUuid(jwt);
////      User user = userRepository.findById(uuid).orElseThrow(() -> new UsernameNotFoundException("User not found"));
////      return ApiResponse.ok("성공했다 이거야", user);
////    } else {
////      return ApiResponse.withError(ErrorCode.UNAUTHORIZED_ERROR);
////    }
////  }
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
//}