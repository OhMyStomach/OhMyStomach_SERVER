package org.ohmystomach.ohmystomach_server.toiletmyplace.application;

import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;
import org.ohmystomach.ohmystomach_server.toiletmyplace.domain.UserToilet;
import org.ohmystomach.ohmystomach_server.domain.toilet.domain.Toilet;
import org.ohmystomach.ohmystomach_server.toiletmyplace.dao.UserToiletRepository;
import org.ohmystomach.ohmystomach_server.domain.toilet.dao.ToiletRepository;
import org.ohmystomach.ohmystomach_server.user.dao.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 사용자가 저장한 화장실 관련 비즈니스 로직을 처리하는 서비스 클래스.
 */
@Service
@RequiredArgsConstructor
public class UserToiletService {
    // UserToiletRepository, ToiletRepository, UserRepository 의존성 주입, 사용자가 저장한 화장실 데이터베이스 작업 처리
    private final UserToiletRepository userToiletRepository;
    private final ToiletRepository toiletRepository;
    private final UserRepository userRepository;

    /**
     * 사용자가 저장한 화장실의 목록을 조회합니다.
     *
     * @param userId 조회할 사용자의 ID.
     * @return 조회된 사용자가 저장한 화장실 목록.
     */
    public List<Toilet> getUserSavedToilets(Long userId) {
        return userToiletRepository.findByUserId(userId).stream()
                .map(UserToilet::getToilet)
                .collect(Collectors.toList());
    }

    /**
     * 새로운 화장실을 사용자의 내 장소로 저장합니다.
     *
     * @param userId 조회할 사용자의 ID.
     * @param toiletId 저장할 화장실의 ID.
     * @return 저장된 화장실 객체.
     */
    public UserToilet saveUserToilet(Long userId, Long toiletId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다. \nID: " + userId));
        Toilet toilet = toiletRepository.findById(toiletId)
                .orElseThrow(() -> new RuntimeException("화장실을 찾을 수 없습니다. \nID: " + toiletId));

        UserToilet userToilet = new UserToilet(user, toilet);
        return userToiletRepository.save(userToilet);
    }

    /**
     * 사용자가 내 장소로 저장한 화장실을 삭제합니다.
     *
     * @param userId 사용자의 ID.
     * @param toiletId 삭제할 화장실의 ID.
     */
    public void deleteUserToilet(Long userId, Long toiletId) {
        UserToilet userToilet = userToiletRepository.findByUserId(userId).stream()
                .filter(ut -> ut.getToilet().getId().equals(toiletId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("UserToilet not found with userId: " + userId + " and toiletId: " + toiletId));
        userToiletRepository.delete(userToilet);
    }

    /**
     * 사용자가 내 장소로 저장한 화장실의 정보를 업데이트합니다.
     *
     * @param userId 사용자의 ID.
     * @param toiletId 업데이트할 화장실의 ID.
     * @param updatedToilet 업데이트할 화장실의 새로운 정보.
     * @return 업데이트된 UserToilet 객체.
     */
    public UserToilet updateUserToilet(Long userId, Long toiletId, Toilet updatedToilet) {
        UserToilet userToilet = userToiletRepository.findByUserId(userId).stream()
                .filter(ut -> ut.getToilet().getId().equals(toiletId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("UserToilet not found with userId: " + userId + " and toiletId: " + toiletId));

        Toilet toilet = userToilet.getToilet();
        toilet.setName(updatedToilet.getName());
        toilet.setCategory(updatedToilet.getCategory());
        toilet.setWsg84x(updatedToilet.getWsg84x());
        toilet.setWsg84y(updatedToilet.getWsg84y());

        toiletRepository.save(toilet);
        return userToilet;
    }
}
