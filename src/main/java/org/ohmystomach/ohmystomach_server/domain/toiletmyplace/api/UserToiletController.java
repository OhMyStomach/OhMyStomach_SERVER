package org.ohmystomach.ohmystomach_server.domain.toiletmyplace.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.ohmystomach.ohmystomach_server.domain.oauth.application.JWTService;
import org.ohmystomach.ohmystomach_server.domain.toiletmyplace.application.UserToiletService;
import org.ohmystomach.ohmystomach_server.domain.toiletmyplace.domain.UserToilet;
import org.ohmystomach.ohmystomach_server.domain.toiletmyplace.dto.request.CreateUserToiletRequestDto;
import org.ohmystomach.ohmystomach_server.domain.toiletmyplace.dto.request.UpdateUserToiletRequestDto;
import org.ohmystomach.ohmystomach_server.global.common.response.ApiResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 사용자가 저장한 화장실 데이터와 관련된 요청을 처리하는 컨트롤러.
 */
@Tag(name = "내 장소 - 화장실 API", description = "내 화장실 생성, 조회, 수정, 삭제")
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
    @Operation(summary = "내 화장실 정보 조회 api", description = "사용자가 내 장소로 저장한 화장실 목록을 조회합니다.")
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
    @Operation(summary = "내 화장실 정보 생성 api", description = "새로운 화장실을 사용자의 내 장소로 저장합니다.")
    @PostMapping("/save")
    public ApiResponse<UserToilet> createUserToilet(@RequestHeader("Authorization") String token,
                                                    @RequestBody CreateUserToiletRequestDto dto) {
        return userToiletService.createUserToilet(dto.toServiceDto(jwtService.decodeToken(token)));
    }

    /**
     * 사용자가 내 장소로 저장한 화장실을 삭제합니다.
     *
     * @param token 사용자의 ID.
     * @param toiletId 삭제할 화장실의 ID.
     * @return 삭제 결과 메시지를 포함하는 ApiResponse.
     */
    @Operation(summary = "내 화장실 정보 삭제 api", description = "사용자가 내 장소로 저장한 화장실을 삭제합니다.")
    @DeleteMapping("/{toiletId}")
    public ApiResponse<String> deleteUserToilet(@RequestHeader("Authorization") String token,
                                                @PathVariable Long toiletId) {
        return userToiletService.deleteUserToilet(jwtService.decodeToken(token), toiletId);
    }

    /**
     * 사용자가 내 장소로 저장한 화장실의 정보를 업데이트합니다.
     *
     * @param dto 사용자의 ID.
     * @param dto 업데이트할 화장실의 ID.
     * @param dto 업데이트할 화장실의 새로운 정보.
     * @return 업데이트된 UserToilet 객체를 포함하는 ApiResponse.
     */
    @Operation(summary = "내 화장실 정보 수정 api", description = "사용자가 내 장소로 저장한 화장실의 정보를 업데이트합니다.")
    @PutMapping("/edit")
    public ApiResponse<UserToilet> updateUserToilet(@RequestHeader("Authorization") String token,
                                                    @RequestBody UpdateUserToiletRequestDto dto) {
        return userToiletService.updateUserToilet(dto.toServiceDto(jwtService.decodeToken(token)));
    }
}
