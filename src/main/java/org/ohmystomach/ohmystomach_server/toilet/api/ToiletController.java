package org.ohmystomach.ohmystomach_server.toilet.api;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.ohmystomach.ohmystomach_server.global.common.response.ApiResponse;
import org.ohmystomach.ohmystomach_server.toilet.application.ToiletService;
import org.ohmystomach.ohmystomach_server.toilet.domain.Toilet;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 공중화장실 데이터와 관련된 요청을 처리하는 컨트롤러.
 */
@RestController
@RequestMapping("/api/toilets")
@RequiredArgsConstructor
public class ToiletController {

  // ToiletService 의존성 주입, 비즈니스 로직을 처리
  private final ToiletService toiletService;

  /**
   * 모든 공중화장실 목록을 반환합니다.
   *
   * @return 모든 Toilet 객체의 리스트를 포함하는 ApiResponse.
   */
  @GetMapping
  public ApiResponse<List<Toilet>> getAllToilets() {
    // 서비스에서 모든 공중화장실을 가져와 ApiResponse로 감싸서 반환
    return ApiResponse.ok(toiletService.getAllToilets());
  }

  /**
   * 특정 ID로 공중화장실을 조회합니다.
   *
   * @param id 조회할 공중화장실의 ID.
   * @return 요청된 Toilet 객체를 포함하는 ApiResponse.
   */
  @GetMapping("/{id}")
  public ApiResponse<Toilet> getToiletById(@PathVariable Long id) {
    // 서비스에서 ID로 공중화장실을 찾아 ApiResponse로 감싸서 반환
    return ApiResponse.ok(toiletService.getToiletById(id));
  }
}
