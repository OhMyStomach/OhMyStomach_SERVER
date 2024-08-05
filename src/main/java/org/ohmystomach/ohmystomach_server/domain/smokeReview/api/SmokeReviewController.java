package org.ohmystomach.ohmystomach_server.domain.smokeReview.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.ohmystomach.ohmystomach_server.domain.oauth.application.JWTService;
import org.ohmystomach.ohmystomach_server.domain.smokeReview.domain.SmokeReview;
import org.ohmystomach.ohmystomach_server.global.common.response.ApiResponse;
import org.ohmystomach.ohmystomach_server.domain.smokeReview.dto.request.CreateSmokeReviewRequestDto;
import org.ohmystomach.ohmystomach_server.domain.smokeReview.application.SmokeReviewService;
import org.ohmystomach.ohmystomach_server.domain.smokeReview.dto.request.UpdateSmokeReviewRequestDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "흡연구역 후기 API", description = "흡연구역 후기 생성, 조회, 수정, 삭제")
@RestController
@RequestMapping("/api/smoke/reviews")
@RequiredArgsConstructor
public class SmokeReviewController {

  // SmokeReviewService 의존성 주입, 리뷰 관련 비즈니스 로직 처리
  private final SmokeReviewService reviewService;
  private final JWTService jwtService;
  /**
   * 특정 흡연구역의 리뷰 목록을 조회합니다.
   *
   * @param smokeId 조회할 흡연구역의 ID.
   * @param sort 정렬 기준 (latest, ratingDesc, ratingAsc). 기본값은 "latest".
   * @return 해당 흡연구역의 리뷰 리스트를 포함하는 ApiResponse.
   */
  @Operation(summary = "특정 흡연구역의 후기 전체 조회 API")
  @GetMapping("/{smokeId}/all")
  public ApiResponse<List<SmokeReview>> getReviewsByToiletId(@PathVariable Long smokeId,
                                                             @RequestParam(defaultValue = "latest") String sort) {
    return reviewService.getReviewsBySmokeId(smokeId, sort);
  }



  /**
   * 새로운 리뷰를 등록합니다.
   *
   * @param dto 등록할 리뷰 객체.
   * @return 등록된 리뷰 객체를 포함하는 ApiResponse.
   */
  @Operation(summary = "흡연구역 후기 생성(등록) API", description = "  /**\n" +
          "   * 특정 흡연구역의 리뷰 목록을 조회합니다.\n" +
          "   *\n" +
          "   * @param toiletId 조회할 화장실의 ID.\n" +
          "   * @param sort 정렬 기준 (ratingDesc: 별점 높은 순, ratingAsc: 별점 낮은 순, default: 최신순).\n" +
          "   * @return 정렬 기준에 따라 정렬된 리뷰 리스트.\n" +
          "   */")
  @PostMapping
  public ApiResponse<SmokeReview> addReview(@RequestBody CreateSmokeReviewRequestDto dto) {
    return reviewService.addReview(dto.toServiceRequest(jwtService.decodeToken(dto.token())));
  }



  /**
   * 기존 리뷰를 수정합니다.
   *
   * @param id 수정할 리뷰의 ID.
   * @param dto 수정할 리뷰 정보를 포함한 객체.
   * @return 수정된 리뷰 객체를 포함하는 ApiResponse.
   */
  @Operation(summary = "흡연구역 후기 수정 API")
  @PutMapping("/{id}")
  public ApiResponse<SmokeReview> updateReview(@PathVariable Long id, @RequestBody UpdateSmokeReviewRequestDto dto) {
    return reviewService.updateReview(id, dto.toServiceRequest());
  }



  /**
   * 특정 리뷰를 삭제합니다.
   *
   * @param id 삭제할 리뷰의 ID.
   * @return 삭제 성공 메시지를 포함하는 ApiResponse.
   */
  @Operation(summary = "흡연구역 후기 삭제 API")
  @DeleteMapping("/{id}")
  public ApiResponse<String> deleteReview(@RequestHeader("Authorization") String token,
                                          @PathVariable Long id) {
    return reviewService.deleteReview(jwtService.decodeToken(token), id);
  }
}
