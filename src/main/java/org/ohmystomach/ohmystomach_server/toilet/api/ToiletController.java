package org.ohmystomach.ohmystomach_server.toilet.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.ohmystomach.ohmystomach_server.global.common.response.ApiResponse;
import org.ohmystomach.ohmystomach_server.toilet.application.ToiletService;
import org.ohmystomach.ohmystomach_server.toilet.domain.Toilet;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
  public ApiResponse<List<Toilet>> getAllToilets() {
    // 서비스에서 모든 공중화장실을 가져와 ApiResponse로 감싸서 반환
    return toiletService.getAllToilets();
  }

  @Operation(summary = "ID로 공중화장실 조회 API")
  @GetMapping("/{id}")
  public ApiResponse<Toilet> getToiletById(@PathVariable Long id) {
    // 서비스에서 ID로 공중화장실을 찾아 ApiResponse로 감싸서 반환
    return toiletService.getToiletById(id);
  }
}
