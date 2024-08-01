package org.ohmystomach.ohmystomach_server.smokemyplace.application;

import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;
import org.ohmystomach.ohmystomach_server.smokemyplace.domain.UserSmoke;
import org.ohmystomach.ohmystomach_server.domain.smoke.domain.Smoke;
import org.ohmystomach.ohmystomach_server.smokemyplace.dao.UserSmokeRepository;
import org.ohmystomach.ohmystomach_server.domain.smoke.dao.SmokeRepository;
import org.ohmystomach.ohmystomach_server.user.dao.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 사용자가 저장한 흡연구역 관련 비즈니스 로직을 처리하는 서비스 클래스.
 */
@Service
@RequiredArgsConstructor
public class UserSmokeService {
    // UserSmokeRepository, SmokeRepository, UserRepository 의존성 주입, 사용자가 저장한 흡연구역 데이터베이스 작업 처리
    private final UserSmokeRepository userSmokeRepository;
    private final SmokeRepository smokeRepository;
    private final UserRepository userRepository;

    /**
     * 사용자가 저장한 흡연구역의 목록을 조회합니다.
     *
     * @param userId 조회할 사용자의 ID.
     * @return 조회된 사용자가 저장한 흡연구역 목록.
     */
    public List<Smoke> getUserSavedSmokes(Long userId) {
        return userSmokeRepository.findByUserId(userId).stream()
                .map(UserSmoke::getSmoke)
                .collect(Collectors.toList());
    }

    /**
     * 새로운 흡연구역을 사용자의 내 장소로 저장합니다.
     *
     * @param userId 조회할 사용자의 ID.
     * @param smokeId 저장할 흡연구역의 ID.
     * @return 저장된 화장실 객체.
     */
    public UserSmoke saveUserSmoke(Long userId, Long smokeId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다. \nID: " + userId));
        Smoke smoke = smokeRepository.findById(smokeId)
                .orElseThrow(() -> new RuntimeException("흡연구역을 찾을 수 없습니다. \nID: " + smokeId));

        UserSmoke userSmoke = new UserSmoke(user, smoke);
        return userSmokeRepository.save(userSmoke);
    }

    /**
     * 사용자가 내 장소로 저장한 흡연구역을 삭제합니다.
     *
     * @param userId 사용자의 ID.
     * @param smokeId 삭제할 흡연구역의 ID.
     */
    public void deleteUserSmoke(Long userId, Long smokeId) {
        UserSmoke userSmoke = userSmokeRepository.findByUserId(userId).stream()
                .filter(us -> us.getSmoke().getId().equals(smokeId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("UserSmoke not found with userId: " + userId + " and smokeId: " + smokeId));
        userSmokeRepository.delete(userSmoke);
    }

    /**
     * 사용자가 내 장소로 저장한 흡연구역의 정보를 업데이트합니다.
     *
     * @param userId 사용자의 ID.
     * @param smokeId 업데이트할 흡연구역의 ID.
     * @param updatedSmoke 업데이트할 화장실의 새로운 정보.
     * @return 업데이트된 UserSmoke 객체.
     */
    public UserSmoke updateUserSmoke(Long userId, Long smokeId, Smoke updatedSmoke) {
        UserSmoke userSmoke = userSmokeRepository.findByUserId(userId).stream()
                .filter(us -> us.getSmoke().getId().equals(smokeId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("UserSmoke not found with userId: " + userId + " and smokeId: " + smokeId));

        Smoke smoke = userSmoke.getSmoke();
        smoke.setDistrict(updatedSmoke.getDistrict());
        smoke.setType(updatedSmoke.getType());
        smoke.setLocation(updatedSmoke.getLocation());

        smokeRepository.save(smoke);
        return userSmoke;
    }
}
