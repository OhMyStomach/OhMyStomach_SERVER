package org.ohmystomach.ohmystomach_server.domain.toiletmyplace.api;

import lombok.RequiredArgsConstructor;
import org.ohmystomach.ohmystomach_server.domain.oauth.application.JWTService;
import org.ohmystomach.ohmystomach_server.domain.toiletmyplace.dto.request.CreateUserToiletRequestDto;
import org.ohmystomach.ohmystomach_server.domain.toiletmyplace.dto.request.UpdateUserToiletRequestDto;
import org.springframework.web.bind.annotation.*;
import org.ohmystomach.ohmystomach_server.domain.toilet.domain.Toilet;
import org.ohmystomach.ohmystomach_server.domain.toiletmyplace.domain.UserToilet;
import org.ohmystomach.ohmystomach_server.domain.toiletmyplace.application.UserToiletService;
import org.ohmystomach.ohmystomach_server.global.common.response.ApiResponse;

import java.util.List;

/**
 * 사용자가 저장한 화장실 데이터와 관련된 요청을 처리하는 컨트롤러.
 */
@RestController
@RequestMapping("/api/my/toilet")
@RequiredArgsConstructor
public class UserToiletController {
    // UserToiletService 의존성 주입, 사용자가 저장한 화장실 관련 비즈니스 로직 처리
    private final UserToiletService userToiletService;
    private final JWTService jwtService;

    /**
     * 사용자가 내 장소로 저장한 화장실 목록을 조회합니다.
     *
     * @param token 사용자의 ID.
     * @return 사용자가 저장한 화장실 목록을 포함하는 ApiResponse.
     */
    @GetMapping("/all")
    public ApiResponse<List<UserToilet>> retrieveUserSavedToilets(@RequestHeader("Authorization") String token) {
        return userToiletService.retrieveUserSavedToilets(jwtService.decodeToken(token));
    }

    /**
     * 새로운 화장실을 사용자의 내 장소로 저장합니다.
     *
     * @param dto 사용자의 ID.
     * @param dto 요청 본문으로 전달된 화장실 객체.
     * @return 저장된 UserToilet 객체를 포함하는 ApiResponse.
     */
    @PostMapping("/save")
    public ApiResponse<UserToilet> createUserToilet(@RequestBody CreateUserToiletRequestDto dto) {
        return userToiletService.createUserToilet(dto.toServiceDto(jwtService.decodeToken(dto.token())));
    }

    /**
     * 사용자가 내 장소로 저장한 화장실을 삭제합니다.
     *
     * @param userId 사용자의 ID.
     * @param toiletId 삭제할 화장실의 ID.
     * @return 삭제 결과 메시지를 포함하는 ApiResponse.
     */
    @DeleteMapping("/{toiletId}")
    public ApiResponse<Void> deleteUserToilet(@RequestHeader("Authorization") String token, @PathVariable Long toiletId) {
        return userToiletService.deleteUserToilet(jwtService.decodeToken(token), toiletId);
    }

    /**
     * 사용자가 내 장소로 저장한 화장실의 정보를 업데이트합니다.
     *
     * @param userId 사용자의 ID.
     * @param toiletId 업데이트할 화장실의 ID.
     * @param updatedToilet 업데이트할 화장실의 새로운 정보.
     * @return 업데이트된 UserToilet 객체를 포함하는 ApiResponse.
     */
    @PutMapping("/{userId}/{toiletId}")
    public ApiResponse<UserToilet> updateUserToilet(@RequestBody UpdateUserToiletRequestDto dto) {
        return userToiletService.updateUserToilet(dto.toServiceDto(jwtService.decodeToken(dto.token())));
    }
}
