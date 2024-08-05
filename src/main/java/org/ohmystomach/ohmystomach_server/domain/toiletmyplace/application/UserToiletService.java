package org.ohmystomach.ohmystomach_server.domain.toiletmyplace.application;

import lombok.RequiredArgsConstructor;
import org.ohmystomach.ohmystomach_server.domain.toiletmyplace.dao.UserToiletRepository;
import org.ohmystomach.ohmystomach_server.domain.toiletmyplace.domain.UserToilet;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.ohmystomach.ohmystomach_server.domain.toilet.domain.Toilet;
import org.ohmystomach.ohmystomach_server.global.common.response.ApiResponse;

import java.util.*;

/**
 * 사용자가 저장한 화장실 관련 비즈니스 로직을 처리하는 서비스 클래스.
 */
@Service
@RequiredArgsConstructor
public class UserToiletService {
    private final UserToiletRepository userToiletRepository;

    // In-memory storage for user-specific toilets
//    private final Map<String, List<UserToilet>> userToiletMap = new HashMap<>();

    /**
     * 사용자가 저장한 화장실의 목록을 조회합니다.
     *
     * @param uuid 조회할 사용자의 ID.
     * @return 조회된 사용자가 저장한 화장실 목록을 포함하는 ApiResponse.
     */
    public ApiResponse<List<UserToilet>> retrieveUserSavedToilets(String uuid) {
        List<UserToilet> userToilets = userToiletRepository.findByUserId(uuid);
//        List<UserToilet> userToilets = userToiletMap.getOrDefault(uuid, new ArrayList<>());
//        List<Toilet> toilets = userToilets.stream()
//                .map(UserToilet::getToilet)
//                .collect(Collectors.toList());
        return ApiResponse.ok("나의 화장실 목록을 성공적으로 조회했습니다.", userToilets);
    }

    /**
     * 새로운 화장실을 사용자의 내 장소로 저장합니다.
     *
     * @param userId   조회할 사용자의 ID.
     * @param toilet   저장할 화장실 객체.
     * @return 저장된 UserToilet 객체를 포함하는 ApiResponse.
     */
    public ApiResponse<UserToilet> createUserToilet(Long userId, Toilet toilet) {
//        List<UserToilet> userToilets = userToiletMap.computeIfAbsent(userId, k -> new ArrayList<>());
//
//        Toilet toiletToSave = null;
//
//        // Check if the toilet object has an ID and retrieve it from the database
//        if (toilet.getId() != null) {
//            toiletToSave = toiletRepository.findById(toilet.getId())
//                    .orElse(null);
//        }
//
//        if (toiletToSave == null) {
//            // If it's a new toilet area, use the provided object as is
//            toiletToSave = toilet;
//        }
//
//        UserToilet userToilet = new UserToilet(userId, toiletToSave);
//        userToilets.add(userToilet);

        return ApiResponse.ok("화장실이 성공적으로 저장되었습니다.", userToilet);
    }

    /**
     * 사용자가 내 장소로 저장한 화장실을 삭제합니다.
     *
     * @param userId   사용자의 ID.
     * @param toiletId 삭제할 화장실의 ID.
     * @return ApiResponse 삭제 결과.
     */
    public ApiResponse<Void> deleteUserToilet(Long userId, Long toiletId) {
        List<UserToilet> userToilets = userToiletMap.get(userId);

        if (userToilets != null) {
            boolean removed = userToilets.removeIf(ut -> ut.getToilet().getId().equals(toiletId));

            if (removed) {
                return ApiResponse.ok("화장실이 성공적으로 삭제되었습니다.");
            }
        }

        return ApiResponse.of(HttpStatus.NOT_FOUND, "화장실을 찾을 수 없습니다.\n사용자 ID: " + userId + "\n화장실 ID: " + toiletId, null);
    }

    /**
     * 사용자가 내 장소로 저장한 화장실의 정보를 업데이트합니다.
     *
     * @param userId        사용자의 ID.
     * @param toiletId      업데이트할 화장실의 ID.
     * @param updatedToilet 업데이트할 화장실의 새로운 정보.
     * @return 업데이트된 UserToilet 객체를 포함하는 ApiResponse.
     */
    public ApiResponse<UserToilet> updateUserToilet(Long userId, Long toiletId, Toilet updatedToilet) {
        List<UserToilet> userToilets = userToiletMap.get(userId);

        if (userToilets != null) {
            for (UserToilet userToilet : userToilets) {
                if (userToilet.getToilet().getId().equals(toiletId)) {
                    Toilet toilet = userToilet.getToilet();
                    toilet.setName(updatedToilet.getName());
                    toilet.setCategory(updatedToilet.getCategory());
                    toilet.setWsg84x(updatedToilet.getWsg84x());
                    toilet.setWsg84y(updatedToilet.getWsg84y());

                    return ApiResponse.ok("화장실 정보가 성공적으로 업데이트되었습니다.", userToilet);
                }
            }
        }

        return ApiResponse.of(HttpStatus.NOT_FOUND, "화장실을 찾을 수 없습니다.\n사용자 ID: " + userId + "\n화장실 ID: " + toiletId, null);
    }
}
