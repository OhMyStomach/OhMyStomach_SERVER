package org.ohmystomach.ohmystomach_server.domain.smokemyplace.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.ohmystomach.ohmystomach_server.domain.oauth.application.JWTService;
import org.ohmystomach.ohmystomach_server.domain.smokemyplace.application.UserSmokeService;
import org.ohmystomach.ohmystomach_server.domain.smokemyplace.domain.UserSmoke;
import org.ohmystomach.ohmystomach_server.domain.smokemyplace.dto.request.CreateUserSmokeRequestDto;
import org.ohmystomach.ohmystomach_server.domain.smokemyplace.dto.request.UpdateUserSmokeRequestDto;
import org.ohmystomach.ohmystomach_server.global.common.response.ApiResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 사용자가 저장한 흡연구역 데이터와 관련된 요청을 처리하는 컨트롤러.
 */
@Tag(name = "내 장소 - 흡연구역 API", description = "내 흡연구역 생성, 조회, 수정, 삭제")
@RestController
@RequestMapping("/api/my/smoke")
@RequiredArgsConstructor
public class UserSmokeController {
    // UserSmokeService 의존성 주입, 사용자가 저장한 흡연구역 관련 비즈니스 로직 처리
    private final UserSmokeService userSmokeService;
    private final JWTService jwtService;

    /**
     * 사용자가 내 장소로 저장한 흡연구역 목록을 조회합니다.
     *
     * @param token 사용자의 ID.
     * @return 사용자가 저장한 흡연구역 목록을 포함하는 ApiResponse.
     */
    @Operation(summary = "내 흡연구역 정보 조회 api", description = "사용자가 내 장소로 저장한 흡연구역 목록을 조회합니다.")
    @GetMapping("/all")
    public ApiResponse<List<UserSmoke>> retrieveUserSavedSmokes(@RequestHeader("Authorization") String token) {
        return userSmokeService.retrieveUserSavedSmokes(jwtService.decodeToken(token));
    }

    /**
     * 새로운 흡연구역을 사용자의 내 장소로 저장합니다.
     *
     * @param dto 사용자의 ID.
     * @param dto 요청 본문으로 전달된 흡연구역 객체.
     * @return 저장된 UserSmoke 객체를 포함하는 ApiResponse.
     */
    @Operation(summary = "내 흡연구역 정보 생성 api", description = "새로운 흡연구역을 사용자의 내 장소로 저장합니다.")
    @PostMapping("/save")
    public ApiResponse<UserSmoke> createUserSmoke(@RequestBody CreateUserSmokeRequestDto dto) {
        return userSmokeService.createUserSmoke(dto.toServiceDto(jwtService.decodeToken(dto.token())));
    }

    /**
     * 사용자가 내 장소로 저장한 흡연구역을 삭제합니다.
     *
     * @param token 사용자의 ID.
     * @param smokeId 삭제할 Smoke의 ID.
     * @return 삭제 결과 메시지를 포함하는 ApiResponse.
     */
    @Operation(summary = "내 흡연구역 정보 삭제 api", description = "사용자가 내 장소로 저장한 흡연구역을 삭제합니다.")
    @DeleteMapping("/{smokeId}")
    public ApiResponse<String> deleteUserSmoke(@RequestHeader("Authorization") String token,
                                               @PathVariable Long smokeId) {
        return userSmokeService.deleteUserSmoke(jwtService.decodeToken(token), smokeId);
    }

    /**
     * 사용자가 내 장소로 저장한 흡연구역의 정보를 업데이트합니다.
     *
     * @param dto 사용자의 ID.
     * @param dto 업데이트할 흡연구역의 ID.
     * @param dto 업데이트할 흡연구역의 새로운 정보.
     * @return 업데이트된 UserSmoke 객체를 포함하는 ApiResponse.
     */
    @Operation(summary = "내 흡연구역 정보 수정 api", description = "사용자가 내 장소로 저장한 흡연구역의 정보를 업데이트합니다.")
    @PutMapping("/edit")
    public ApiResponse<UserSmoke> updateUserSmoke(@RequestBody UpdateUserSmokeRequestDto dto) {
        return userSmokeService.updateUserSmoke(dto.toServiceDto(jwtService.decodeToken(dto.token())));
    }
}
