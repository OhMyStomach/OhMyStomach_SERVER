package org.ohmystomach.ohmystomach_server.domain.user.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.ohmystomach.ohmystomach_server.domain.user.application.UserService;
import org.ohmystomach.ohmystomach_server.domain.user.domain.User;
import org.ohmystomach.ohmystomach_server.global.common.response.ApiResponse;
import org.springframework.web.bind.annotation.*;

@Tag(name = "사용자 정보 API", description = "닉네임 중복 확인 / 사용자 정보 수정 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @Operation(summary = "사용자 닉네임 중복 확인 API", description = "true일 경우 : 사용가능한 닉네임 / false일 경우 : 사용불가능한 닉네임")
    @GetMapping("/validate")
    public ApiResponse<Boolean> validateNickname(@RequestParam String nickname) {
        return userService.validateUserNickname(nickname);
    }
}
