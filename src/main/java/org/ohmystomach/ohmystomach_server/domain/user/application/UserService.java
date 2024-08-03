package org.ohmystomach.ohmystomach_server.domain.user.application;

import lombok.RequiredArgsConstructor;
import org.ohmystomach.ohmystomach_server.domain.user.dao.UserRepository;
import org.ohmystomach.ohmystomach_server.domain.user.domain.User;
import org.ohmystomach.ohmystomach_server.global.common.response.ApiResponse;
import org.ohmystomach.ohmystomach_server.global.error.ErrorCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;


    public ApiResponse<User> retrieveOrCreateUser(Map<String, Object> userInfo) {
        String uuid = userInfo.get("id").toString();
        Optional<User> optionalUser = userRepository.findById(uuid);
        if(optionalUser.isEmpty()) {
            System.out.println("11111111111111");
            return this.createUser(userInfo);
        }
        User savedUser = optionalUser.get();
        return ApiResponse.ok("사용자 정보를 성공적으로 조회했습니다.", savedUser);
//        User user = userRepository.findById(uuid).orElseGet(() -> {
//            User newUser = new User();
//            newUser.setId(uuid);
//            newUser.setEmail(email);
//            newUser.setNickname(name);
//            return userRepository.save(newUser);
//        });
    }

    private ApiResponse<User> createUser(Map<String, Object> userInfo) {
        Map<String, Object> kakao_account = (Map<String, Object>) userInfo.get("kakao_account");
        Map<String, Object> profile = (Map<String, Object>) kakao_account.get("profile");
        String uuid = userInfo.get("id").toString();
        String email = (String) kakao_account.get("email");
        String profile_image_url = (String) profile.get("profile_image_url");
        String name = (String) profile.get("nickname");
        System.out.println(name);
        User user = User.builder()
                .id(uuid)
                .email(email)
                .nickname(name)
                .profileImageUrl(profile_image_url)
                .build();
        User savedUser = userRepository.save(user);
        return ApiResponse.ok("사용자 정보를 성공적으로 등록했습니다.", savedUser);
    }

    public ApiResponse<User> retrieveUser(String uuid) {
        Optional<User> optionalUser = userRepository.findById(uuid);
        if(optionalUser.isEmpty()) {
            return ApiResponse.withError(ErrorCode.INVALID_USER_ID);
        }
        User savedUser = optionalUser.get();
        return ApiResponse.ok("사용자 정보를 성공적으로 조회했습니다.", savedUser);
    }
}
