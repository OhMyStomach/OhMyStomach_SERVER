package org.ohmystomach.ohmystomach_server.domain.smoke.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.ohmystomach.ohmystomach_server.domain.smoke.application.SmokeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "흡연구역 API", description = "흡연구역 조회")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class SmokeController {
    private final SmokeService smokeService;
//    @Operation(summary = "흡연구역 전체 조회 API (request 페이지, sequence 순)")
//    @GetMapping("/smoke")
//    public ApiResponse<List<Project>> retrieveAllArtworkProject(){
//        return projectService.retrieveAllArtworkProject();
//    }
}
