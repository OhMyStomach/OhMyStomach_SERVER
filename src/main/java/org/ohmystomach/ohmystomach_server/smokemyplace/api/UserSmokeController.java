package org.ohmystomach.ohmystomach_server.smokemyplace.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.ohmystomach.ohmystomach_server.domain.smoke.domain.Smoke;
import org.ohmystomach.ohmystomach_server.smokemyplace.domain.UserSmoke;
import org.ohmystomach.ohmystomach_server.smokemyplace.application.UserSmokeService;

import java.util.List;

/**
 * 사용자가 저장한 흡연구역 데이터와 관련된 요청을 처리하는 컨트롤러.
 */
@RestController
@RequestMapping("/api/user-smokes")
@RequiredArgsConstructor
public class UserSmokeController {
    // UserSmokeService 의존성 주입, 사용자가 저장한 흡연구역 관련 비즈니스 로직 처리
    private final UserSmokeService userSmokeService;

    /**
     * 사용자가 내 장소로 저장한 흡연구역 목록을 조회합니다.
     *
     * @param userId 사용자의 ID.
     * @return 사용자가 저장한 흡연구역 목록.
     */
    @GetMapping("/{userId}")
    public List<Smoke> getUserSavedSmokes(@PathVariable Long userId) {
        return userSmokeService.getUserSavedSmokes(userId);
    }

    /**
     * 새로운 흡연구역을 사용자의 내 장소로 저장합니다.
     *
     * @param userId 사용자의 ID.
     * @param smokeId 저장할 흡연구역의 ID.
     * @return 저장된 UserSmoke 객체.
     */
    @PostMapping("/{userId}/{smokeId}")
    public UserSmoke saveUserSmoke(@PathVariable Long userId, @PathVariable Long smokeId) {
        return userSmokeService.saveUserSmoke(userId, smokeId);
    }

    /**
     * 사용자가 내 장소로 저장한 흡연구역을 삭제합니다.
     *
     * @param userId 사용자의 ID.
     * @param smokeId 삭제할 Smoke의 ID.
     */
    @DeleteMapping("/{userId}/{smokeId}")
    public void deleteUserSmoke(@PathVariable Long userId, @PathVariable Long smokeId) {
        userSmokeService.deleteUserSmoke(userId, smokeId);
    }

    /**
     * 사용자가 내 장소로 저장한 흡연구역의 정보를 업데이트합니다.
     *
     * @param userId 사용자의 ID.
     * @param smokeId 업데이트할 흡연구역의 ID.
     * @param updatedSmoke 업데이트할 흡연구역의 새로운 정보.
     * @return 업데이트된 UserSmoke 객체.
     */
    @PutMapping("/{userId}/{smokeId}")
    public UserSmoke updateUserSmoke(@PathVariable Long userId, @PathVariable Long smokeId, @RequestBody Smoke updatedSmoke) {
        return userSmokeService.updateUserSmoke(userId, smokeId, updatedSmoke);
    }
}
