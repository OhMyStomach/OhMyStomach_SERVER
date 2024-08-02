package org.ohmystomach.ohmystomach_server.domain.oauth.application;

import lombok.RequiredArgsConstructor;
import org.ohmystomach.ohmystomach_server.global.common.response.ApiResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;

@Service
//@RequiredArgsConstructor
public class KakaoService {

    @Value("${security.oauth2.client.registration.kakao.client-id}")
    private String clientId;

    @Value("${security.oauth2.client.registration.kakao.client-secret}")
    private String clientSecret;

    @Value("${security.oauth2.client.registration.kakao.redirect-uri}")
    private String redirectUri;

    @Value("${security.oauth2.client.provider.kakao.token-uri}")
    private String tokenUri;

    @Value("${security.oauth2.client.provider.kakao.user-info-uri}")
    private String userInfoUri;


    private final WebClient webClient;

    public KakaoService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://kauth.kakao.com").build();
    }

    public String getAccessToken(String code) {
        return webClient.post()
                .uri(uriBuilder -> uriBuilder.path("/oauth/token")
                        .queryParam("grant_type", "authorization_code")
                        .queryParam("client_id", clientId)
                        .queryParam("client_secret", clientSecret)
                        .queryParam("redirect_uri", redirectUri)
                        .queryParam("code", code)
                        .build())
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .retrieve()
                .bodyToMono(Map.class)
                .map(response -> (String) response.get("access_token"))
                .block(); // 비동기 처리 시 block() 사용 자제, 여기서는 예제 단순화를 위해 사용
    }

    public Map<String, Object> getUserInfo(String accessToken) {
        return webClient.mutate()
                .baseUrl("https://kapi.kakao.com")
                .build()
                .get()
                .uri("/v2/user/me")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
                .retrieve()
                .bodyToMono(Map.class)
                .block(); // 비동기 처리 시 block() 사용 자제, 여기서는 예제 단순화를 위해 사용
    }
}

//    public OAuthService(WebClient.Builder webClientBuilder) {
//        this.webClient = webClientBuilder.build();
//    }


//    // Access Token을 얻기 위해 카카오 API에 요청
//    public Mono<ApiResponse<Map<String, Object>>> getAccessToken(String code) {
//        return webClient.post()
//                .uri(tokenUrl)
//                .body(BodyInserters.fromFormData("grant_type", "authorization_code")
//                        .with("client_id", clientId)
//                        .with("client_secret", clientSecret)
//                        .with("redirect_uri", redirectUri)
//                        .with("code", code))
//                .retrieve()
//                .bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {})
//                .map(response -> ApiResponse.ok("Access token retrieved", response));
//    }
////    public Mono<ApiResponse<Map<String, Object>>> getAccessToken(String code) {
////        return webClient.post()
////                .uri(tokenUrl)
////                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
////                .body(BodyInserters.fromFormData("grant_type", "authorization_code")
////                        .with("client_id", clientId)
////                        .with("redirect_uri", redirectUri)
////                        .with("code", code)
////                        .with("client_secret", clientSecret))
////                .retrieve()
////                .bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {})
////                .map(response -> ApiResponse.ok("Access token retrieved", response));
////    }
//
////    public Mono<ApiResponse<Map<String, Object>>> getUserInfo(String accessToken) {
////        return webClient.get()
////                .uri(userInfoUrl)
////                .headers(headers -> headers.setBearerAuth(accessToken))
////                .retrieve()
////                .bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {})
////                .map(userInfo -> ApiResponse.ok("User info retrieved", userInfo));
////    }
//    public Mono<ApiResponse<Map<String, Object>>> getUserInfo(String accessToken) {
//        return webClient.get()
//                .uri(userInfoUrl)
//                .headers(headers -> headers.setBearerAuth(accessToken))
//                .retrieve()
//                .bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {})
//                .map(userInfo -> ApiResponse.ok("User info retrieved", userInfo));
//    }
//
//    // Authorization Code를 사용하여 사용자 정보를 가져오는 메서드
//    public Mono<ApiResponse<Map<String, Object>>> getUserInfoFromCode(String code) {
//        return getAccessToken(code)
//                .flatMap(tokenResponse -> {
//                    String accessToken = (String) tokenResponse.getData().get("access_token");
//                    return getUserInfo(accessToken);
//                });
//    }
//}