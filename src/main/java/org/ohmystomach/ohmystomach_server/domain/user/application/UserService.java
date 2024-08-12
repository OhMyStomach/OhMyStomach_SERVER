package org.ohmystomach.ohmystomach_server.domain.user.application;

import lombok.RequiredArgsConstructor;
import org.ohmystomach.ohmystomach_server.domain.oauth.application.KakaoService;
import org.ohmystomach.ohmystomach_server.domain.user.dao.UserRepository;
import org.ohmystomach.ohmystomach_server.domain.user.domain.User;
import org.ohmystomach.ohmystomach_server.domain.user.dto.request.UpdateUserRequestServiceDto;
import org.ohmystomach.ohmystomach_server.global.adapter.S3Adapter;
import org.ohmystomach.ohmystomach_server.global.common.response.ApiResponse;
import org.ohmystomach.ohmystomach_server.global.error.ErrorCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final S3Adapter s3Adapter;
    private final KakaoService kakaoService;

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

    public ApiResponse<Boolean> validateUserNickname(String nickname) {
        List<User> users = userRepository.findByNickname(nickname);
        return ApiResponse.ok("닉네임 중복확인 결과를 성공적으로 조회했습니다.", users.isEmpty());
    }

    public ApiResponse<User> updateUser(UpdateUserRequestServiceDto dto, MultipartFile file) {
        Optional<User> optionalUser = userRepository.findById(dto.uuid());
        if(optionalUser.isEmpty()) {
            return ApiResponse.withError(ErrorCode.INVALID_USER_ID);
        }
        User user = optionalUser.get();
        String profileImageFileName = user.getProfileImageFileName();
        String profileImageUrl = user.getProfileImageUrl();
        if(!file.isEmpty()) {
            // 기존 프로필 이미지 삭제
            // 카카오 프로필 이미지인지 확인(이미지 파일 이름이 없으면 카카오 이미지 -> 바로 이미지 등록해도 됨)
            String fileName = user.getProfileImageFileName();
            if(fileName != null && !fileName.isEmpty()) {
                ApiResponse<String> deleteFileResponse = s3Adapter.deleteFile(fileName);
                if(deleteFileResponse.getStatus().is5xxServerError()) {
                    return ApiResponse.withError(ErrorCode.ERROR_S3_DELETE_OBJECT);
                }
            }
            // 새로운 프로필 이미지 등록
            ApiResponse<String> uploadFileResponse = s3Adapter.uploadImage(file);
            if(uploadFileResponse.getStatus().is5xxServerError()) {
                return ApiResponse.withError(ErrorCode.ERROR_S3_UPDATE_OBJECT);
            }
            profileImageFileName = file.getOriginalFilename();
            profileImageUrl = uploadFileResponse.getData();

        }
        user.update(dto, profileImageFileName, profileImageUrl);
        User savedUser = userRepository.save(user);
        return ApiResponse.ok("사용자 정보를 성공적으로 수정했습니다.", savedUser);
    }

    public ApiResponse<User> invalidateUserSession(String userId) {
        // 세션 무효화 또는 로그아웃 상태로 전환
        Optional<User> optionalUser = userRepository.findById(userId);
        if(optionalUser.isEmpty()) {
            ApiResponse.withError(ErrorCode.INVALID_USER_ID);
        }
        User user = optionalUser.get();
//        User user = userRepository.findById(userId).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        user.setKakaoAccessToken(null);
        user.setKakaoRefreshToken(null);
        User savedUser = userRepository.save(user);
        return ApiResponse.ok("로그아웃 되었습니다.", savedUser);
    }

    public String getValidAccessToken(User user) {
        if (kakaoService.isAccessTokenExpired(user.getKakaoAccessTokenExpiresAt())) {
            String newAccessToken = kakaoService.refreshAccessToken(user.getKakaoRefreshToken());
            user.setKakaoAccessToken(newAccessToken);
            // 토큰의 만료 시간을 갱신
            Long newExpiresAt = 1000000000L;// 새 AccessToken의 만료 시간 계산
            user.setKakaoAccessTokenExpiresAt(newExpiresAt);
            userRepository.save(user);
            return newAccessToken;
        }
        return user.getKakaoAccessToken();
    }
}
