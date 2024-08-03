//package org.ohmystomach.ohmystomach_server.domain.smoke.dto;
//
//import jakarta.security.auth.message.AuthException;
//import lombok.Builder;
//import org.ohmystomach.ohmystomach_server.domain.user.domain.User;
//
//import java.util.Map;
//
//@Builder
//public record OAuth2UserInfo(
//        String name,
//        String email,
//        String profile
//) {
//
////    public static OAuth2UserInfo of(String registrationId, Map<String, Object> attributes) {
////        return switch (registrationId) { // registration id별로 userInfo 생성
////            case "google" -> ofGoogle(attributes);
////            case "kakao" -> ofKakao(attributes);
////            default -> throw new AuthException(ILLEGAL_REGISTRATION_ID);
////        };
////    }
//
//    private static OAuth2UserInfo ofGoogle(Map<String, Object> attributes) {
//        return OAuth2UserInfo.builder()
//                .name((String) attributes.get("name"))
//                .email((String) attributes.get("email"))
//                .profile((String) attributes.get("picture"))
//                .build();
//    }
//
//    private static OAuth2UserInfo ofKakao(Map<String, Object> attributes) {
//        Map<String, Object> account = (Map<String, Object>) attributes.get("kakao_account");
//        Map<String, Object> profile = (Map<String, Object>) account.get("profile");
//
//        return OAuth2UserInfo.builder()
//                .name((String) profile.get("nickname"))
//                .email((String) account.get("email"))
//                .profile((String) profile.get("profile_image_url"))
//                .build();
//    }
//
//    public User toEntity() {
//        return User.builder()
//                .nickname(name)
//                .email(email)
//                .profileImageUrl(profile)
//                .build();
//    }
//}