package org.ohmystomach.ohmystomach_server.toiletmyplace.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.ohmystomach.ohmystomach_server.domain.toilet.domain.Toilet;
import org.ohmystomach.ohmystomach_server.toiletmyplace.domain.UserToilet;
import org.ohmystomach.ohmystomach_server.toiletmyplace.application.UserToiletService;
import org.ohmystomach.ohmystomach_server.global.common.response.ApiResponse;

import java.util.List;

/**
 * 사용자가 저장한 화장실 데이터와 관련된 요청을 처리하는 컨트롤러.
 */
@RestController
@RequestMapping("/api/user-toilets")
@RequiredArgsConstructor
public class UserToiletController {
    // UserToiletService 의존성 주입, 사용자가 저장한 화장실 관련 비즈니스 로직 처리
    private final UserToiletService userToiletService;

    /**
     * 사용자가 내 장소로 저장한 화장실 목록을 조회합니다.
     *
     * @param userId 사용자의 ID.
     * @return 사용자가 저장한 화장실 목록을 포함하는 ApiResponse.
     */
    @GetMapping("/{userId}")
    public ApiResponse<List<Toilet>> getUserSavedToilets(@PathVariable Long userId) {
        return userToiletService.getUserSavedToilets(userId);
    }

    /**
     * 새로운 화장실을 사용자의 내 장소로 저장합니다.
     *
     * @param userId 사용자의 ID.
     * @param toilet 요청 본문으로 전달된 화장실 객체.
     * @return 저장된 UserToilet 객체를 포함하는 ApiResponse.
     */
    @PostMapping("/{userId}")
    public ApiResponse<UserToilet> saveUserToilet(@PathVariable Long userId, @RequestBody Toilet toilet) {
        return userToiletService.saveUserToilet(userId, toilet);
    }

    /**
     * 사용자가 내 장소로 저장한 화장실을 삭제합니다.
     *
     * @param userId 사용자의 ID.
     * @param toiletId 삭제할 화장실의 ID.
     * @return 삭제 결과 메시지를 포함하는 ApiResponse.
     */
    @DeleteMapping("/{userId}/{toiletId}")
    public ApiResponse<Void> deleteUserToilet(@PathVariable Long userId, @PathVariable Long toiletId) {
        return userToiletService.deleteUserToilet(userId, toiletId);
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
    public ApiResponse<UserToilet> updateUserToilet(@PathVariable Long userId, @PathVariable Long toiletId, @RequestBody Toilet updatedToilet) {
        return userToiletService.updateUserToilet(userId, toiletId, updatedToilet);
    }
}
