package org.ohmystomach.ohmystomach_server.toiletReview.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.ohmystomach.ohmystomach_server.global.common.response.ApiResponse;
import org.ohmystomach.ohmystomach_server.toiletReview.application.ToiletReviewService;
import org.ohmystomach.ohmystomach_server.toiletReview.domain.ToiletReview;
import org.ohmystomach.ohmystomach_server.toiletReview.dto.request.CreateToiletReviewRequestDto;
import org.ohmystomach.ohmystomach_server.toiletReview.dto.request.UpdateToiletReviewRequestDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "화장실 후기 API", description = "화장실 후기 생성, 조회, 수정, 삭제")
@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ToiletReviewController {

  // ReviewService 의존성 주입, 리뷰 관련 비즈니스 로직 처리
  private final ToiletReviewService reviewService;
  /**
   * 특정 화장실의 리뷰 목록을 조회합니다.
   *
   * @param toiletId 조회할 화장실의 ID.
   * @param sort 정렬 기준 (latest, ratingDesc, ratingAsc). 기본값은 "latest".
   * @return 해당 화장실의 리뷰 리스트를 포함하는 ApiResponse.
   */
  @Operation(summary = "특정 화장실의 후기 전체 조회 API")
  @GetMapping("/toilets/{toiletId}")
  public ApiResponse<List<ToiletReview>> getReviewsByToiletId(@PathVariable Long toiletId,
                                                              @RequestParam(defaultValue = "latest") String sort) {
    return reviewService.getReviewsByToiletId(toiletId, sort);
  }



  /**
   * 새로운 리뷰를 등록합니다.
   *
   * @param review 등록할 리뷰 객체.
   * @return 등록된 리뷰 객체를 포함하는 ApiResponse.
   */
  @Operation(summary = "화장실 후기 생성(등록) API", description = "  /**\n" +
          "   * 특정 화장실의 리뷰 목록을 조회합니다.\n" +
          "   *\n" +
          "   * @param toiletId 조회할 화장실의 ID.\n" +
          "   * @param sort 정렬 기준 (ratingDesc: 별점 높은 순, ratingAsc: 별점 낮은 순, default: 최신순).\n" +
          "   * @return 정렬 기준에 따라 정렬된 리뷰 리스트.\n" +
          "   */")
  @PostMapping
  public ApiResponse<ToiletReview> addReview(@RequestBody CreateToiletReviewRequestDto dto) {

    return reviewService.addReview(dto.toServiceRequest());
  }



  /**
   * 기존 리뷰를 수정합니다.
   *
   * @param id 수정할 리뷰의 ID.
   * @param updatedReview 수정할 리뷰 정보를 포함한 객체.
   * @return 수정된 리뷰 객체를 포함하는 ApiResponse.
   */
  @Operation(summary = "화장실 후기 수정 API")
  @PutMapping("/{id}")
  public ApiResponse<ToiletReview> updateReview(@PathVariable Long id, @RequestBody UpdateToiletReviewRequestDto dto) {
    return reviewService.updateReview(id, dto.toServiceRequest());
  }



  /**
   * 특정 리뷰를 삭제합니다.
   *
   * @param id 삭제할 리뷰의 ID.
   * @return 삭제 성공 메시지를 포함하는 ApiResponse.
   */
  @Operation(summary = "화장실 후기 삭제 API")
  @DeleteMapping("/{id}")
  public ApiResponse<String> deleteReview(@PathVariable Long id) {
    return reviewService.deleteReview(id);
  }
}
