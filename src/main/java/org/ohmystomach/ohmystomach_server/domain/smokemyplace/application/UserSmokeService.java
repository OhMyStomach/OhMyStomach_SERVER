package org.ohmystomach.ohmystomach_server.domain.smokemyplace.application;

import lombok.RequiredArgsConstructor;
import org.ohmystomach.ohmystomach_server.domain.smokemyplace.dao.UserSmokeRepository;
import org.ohmystomach.ohmystomach_server.domain.smokemyplace.domain.UserSmoke;
import org.ohmystomach.ohmystomach_server.domain.smokemyplace.dto.request.CreateUserSmokeServiceRequestDto;
import org.ohmystomach.ohmystomach_server.domain.smokemyplace.dto.request.UpdateUserSmokeServiceRequestDto;
import org.ohmystomach.ohmystomach_server.domain.user.application.UserService;
import org.ohmystomach.ohmystomach_server.domain.user.domain.User;
import org.ohmystomach.ohmystomach_server.global.common.response.ApiResponse;
import org.ohmystomach.ohmystomach_server.global.error.ErrorCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * 사용자가 저장한 흡연구역 관련 비즈니스 로직을 처리하는 서비스 클래스.
 */
@Service
@RequiredArgsConstructor
@Transactional
public class UserSmokeService {
    private final UserSmokeRepository userSmokeRepository;

    private final UserService userService;

    // In-memory storage for user-specific smoking areas
//    private final Map<Long, List<UserSmoke>> userSmokeMap = new HashMap<>();

    /**
     * 사용자가 저장한 흡연구역의 목록을 조회합니다.
     *
     * @param uuid 조회할 사용자의 ID.
     * @return 조회된 사용자가 저장한 흡연구역 목록을 포함하는 ApiResponse.
     */
    public ApiResponse<List<UserSmoke>> retrieveUserSavedSmokes(String uuid) {
        List<UserSmoke> userSmokes = userSmokeRepository.findByUserId(uuid);
        return ApiResponse.ok("나의 흡연구역 목록을 성공적으로 조회했습니다.", userSmokes);
//        List<UserSmoke> userSmokes = userSmokeMap.getOrDefault(userId, new ArrayList<>());
//        List<Smoke> smokes = userSmokes.stream()
//                .map(UserSmoke::getSmoke)
//                .collect(Collectors.toList());
//        return ApiResponse.ok("흡연구역 목록을 성공적으로 조회했습니다.", smokes);
    }

    /**
     * 새로운 흡연구역을 사용자의 내 장소로 저장합니다.
     *
     * @param dto 조회할 사용자의 ID.
     * @param dto  저장할 흡연구역 객체.
     * @return 저장된 UserSmoke 객체를 포함하는 ApiResponse.
     */
    public ApiResponse<UserSmoke> createUserSmoke(CreateUserSmokeServiceRequestDto dto) {
        User user = userService.retrieveUser(dto.uuid()).getData();
        UserSmoke userSmoke = dto.toEntity(user);
        UserSmoke savedSmoke = userSmokeRepository.save(userSmoke);
        return ApiResponse.ok("나의 흡연구역을 성공적으로 등록했습니다.", savedSmoke);
        // 사용자에 대한 흡연구역 리스트를 가져오거나 새로 생성합니다.
//        List<UserSmoke> userSmokes = userSmokeMap.computeIfAbsent(userId, k -> new ArrayList<>());
//
//        Smoke existingSmoke = null;
//        // 기존 Smoke 엔티티에서 주어진 smokeId를 통해 이미 존재하는지를 확인합니다.
//        if (smoke.getId() != null) {
//            existingSmoke = smokeRepository.findById(smoke.getId())
//                    .orElse(null);
//        }
//
//        // 기존 장소인지 확인합니다.
//        Smoke smokeToSave;
//        if (existingSmoke != null) {
//            // 이미 존재하는 장소를 사용합니다.
//            smokeToSave = existingSmoke;
//        } else {
//            // 새로운 장소 정보를 사용합니다.
//            smokeToSave = smoke;
//        }
//
//        // UserSmoke 객체를 생성하고 사용자의 목록에 추가합니다.
//        UserSmoke userSmoke = new UserSmoke(userId, smokeToSave);
//        userSmokes.add(userSmoke);

//        return ApiResponse.ok("흡연구역이 성공적으로 저장되었습니다.", userSmoke);
    }


    /**
     * 사용자가 내 장소로 저장한 흡연구역을 삭제합니다.
     *
     * @param uuid 사용자의 ID.
     * @param smokeId 삭제할 흡연구역의 ID.
     * @return 삭제 결과를 포함하는 ApiResponse.
     */
    public ApiResponse<String> deleteUserSmoke(String uuid, Long smokeId) {
        if(!userSmokeRepository.existsByIdAndUserId(smokeId, uuid)){
            return ApiResponse.withError(ErrorCode.INVALID_USER_SMOKE_ID);
        }
        userSmokeRepository.deleteById(smokeId);
        return ApiResponse.ok("흡연구역이 성공적으로 삭제되었습니다.");

//        List<UserSmoke> userSmokes = userSmokeMap.get(userId);
//
//        if (userSmokes != null) {
//            boolean removed = userSmokes.removeIf(us -> us.getSmoke().getId().equals(smokeId));
//
//            if (removed) {
//                return ApiResponse.ok("흡연구역이 성공적으로 삭제되었습니다.");
//            }
//        }
//
//        return ApiResponse.of(HttpStatus.NOT_FOUND, "흡연구역을 찾을 수 없습니다.\n사용자 ID: " + userId + "\n흡연구역 ID: " + smokeId, null);
    }

    /**
     * 사용자가 내 장소로 저장한 흡연구역의 정보를 업데이트합니다.
     *
     * @param dto 사용자의 ID.
     * @param dto 업데이트할 흡연구역의 ID.
     * @param dto 업데이트할 흡연구역의 새로운 정보.
     * @return 업데이트된 UserSmoke 객체를 포함하는 ApiResponse.
     */
    public ApiResponse<UserSmoke> updateUserSmoke(UpdateUserSmokeServiceRequestDto dto) {
        Optional<UserSmoke> optionalUserToilet = userSmokeRepository.findByIdAndUserId(dto.smokeId(), dto.uuid());
        if(optionalUserToilet.isEmpty()) {
            return ApiResponse.withError(ErrorCode.INVALID_USER_SMOKE_ID);
        }
        UserSmoke userSmoke = optionalUserToilet.get();
        userSmoke.update(dto);
        UserSmoke savedUserSmoke = userSmokeRepository.save(userSmoke);
        return ApiResponse.ok("나의 흡연구역을 성공적으로 수정했습니다.", savedUserSmoke);
//        List<UserSmoke> userSmokes = userSmokeMap.get(userId);
//
//        if (userSmokes != null) {
//            for (UserSmoke userSmoke : userSmokes) {
//                if (userSmoke.getSmoke().getId().equals(smokeId)) {
//                    Smoke smoke = userSmoke.getSmoke();
//                    smoke.setDistrict(updatedSmoke.getDistrict());
//                    smoke.setType(updatedSmoke.getType());
//                    smoke.setLocation(updatedSmoke.getLocation());
//
//                    return ApiResponse.ok("흡연구역 정보가 성공적으로 업데이트되었습니다.", userSmoke);
//                }
//            }
//        }
//
//        return ApiResponse.of(HttpStatus.NOT_FOUND, "사용자가 저장한 흡연구역을 찾을 수 없습니다.\n사용자 ID: " + userId + "\n흡연구역 ID: " + smokeId, null);
    }
}
