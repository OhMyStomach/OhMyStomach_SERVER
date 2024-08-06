package org.ohmystomach.ohmystomach_server.domain.toilet.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.ohmystomach.ohmystomach_server.domain.toilet.application.ToiletService;
import org.ohmystomach.ohmystomach_server.domain.toilet.domain.Toilet;
import org.ohmystomach.ohmystomach_server.global.common.response.ApiResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "공중화장실 API", description = "공중화장실 조회")
@RestController
@RequestMapping("/api/toilets")
@RequiredArgsConstructor
public class ToiletController {

  // ToiletService 의존성 주입, 비즈니스 로직을 처리
  private final ToiletService toiletService;

  @Operation(summary = "공중화장실 전체 조회 API")
  @GetMapping
  public ApiResponse<List<Toilet>> retrieveAllToilets() {
    // 서비스에서 모든 공중화장실을 가져와 ApiResponse로 감싸서 반환
    return toiletService.retrieveAllToilets();
  }

  @Operation(summary = "ID로 공중화장실 조회 API")
  @GetMapping("/{id}")
  public ApiResponse<Toilet> retrieveToiletById(@PathVariable Long id) {
    // 서비스에서 ID로 공중화장실을 찾아 ApiResponse로 감싸서 반환
    return toiletService.retrieveToiletById(id);
  }

//  @Operation(summary = "데이터 기입 API")
//  @GetMapping("/pushdata")
//  public ApiResponse<String> pushData() {
//    toiletService.pushData();
//    return ApiResponse.ok("성공함 ㅋㅋㅋ 못할 줄 알았냐?");
//  }
}
