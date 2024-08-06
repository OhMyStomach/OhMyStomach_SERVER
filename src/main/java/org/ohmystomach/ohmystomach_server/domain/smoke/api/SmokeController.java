package org.ohmystomach.ohmystomach_server.domain.smoke.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.ohmystomach.ohmystomach_server.domain.smoke.application.SmokeService;
import org.ohmystomach.ohmystomach_server.domain.smoke.domain.Smoke;
import org.ohmystomach.ohmystomach_server.global.common.response.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "흡연구역 API", description = "흡연구역 조회")
@RestController
@RequestMapping("/api/smoke")
@RequiredArgsConstructor
public class SmokeController {
    private final SmokeService smokeService;
    @Operation(summary = "흡연구역 전체 조회 API")
    @GetMapping("/all")
    public ApiResponse<List<Smoke>> retrieveAllSmoke(){
        return smokeService.retrieveAllSmoke();
    }

    @Operation(summary = "ID로 흡연구역 조회 API")
    @GetMapping("/{id}")
    public ApiResponse<Smoke> retrieveSmokeById(@PathVariable Long id) {
        // 서비스에서 ID로 공중화장실을 찾아 ApiResponse로 감싸서 반환
        return smokeService.retrieveSmokeById(id);
    }

//    @Operation(summary = "데이터 기입 API")
//    @GetMapping("/pushdata")
//    public ApiResponse<String> pushData() {
//        smokeService.pushData();
//        return ApiResponse.ok("성공함 ㅋㅋㅋ 못할 줄 알았냐?");
//    }

}
