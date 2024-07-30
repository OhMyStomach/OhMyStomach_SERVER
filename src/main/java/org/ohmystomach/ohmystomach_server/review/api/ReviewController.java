package org.ohmystomach.ohmystomach_server.review.api;

import lombok.RequiredArgsConstructor;
import org.ohmystomach.ohmystomach_server.global.common.response.ApiResponse;
import org.ohmystomach.ohmystomach_server.review.application.ReviewService;
import org.ohmystomach.ohmystomach_server.review.domain.Review;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 리뷰 관련 API 요청을 처리하는 컨트롤러 클래스.
 */
@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

  // ReviewService 의존성 주입, 리뷰 관련 비즈니스 로직 처리
  private final ReviewService reviewService;



  /**
   * 특정 화장실의 리뷰 목록을 조회합니다.
   *
   * @param toiletId 조회할 화장실의 ID.
   * @param sort 정렬 기준 (latest, ratingDesc, ratingAsc). 기본값은 "latest".
   * @return 해당 화장실의 리뷰 리스트를 포함하는 ApiResponse.
   */
  @GetMapping("/toilets/{toiletId}")
  public ApiResponse<List<Review>> getReviewsByToiletId(@PathVariable Long toiletId,
      @RequestParam(defaultValue = "latest") String sort) {
    return ApiResponse.ok(reviewService.getReviewsByToiletId(toiletId, sort));
  }



  /**
   * 새로운 리뷰를 등록합니다.
   *
   * @param review 등록할 리뷰 객체.
   * @return 등록된 리뷰 객체를 포함하는 ApiResponse.
   */
  @PostMapping
  public ApiResponse<Review> addReview(@RequestBody Review review) {
    return ApiResponse.ok(reviewService.addReview(review));
  }



  /**
   * 기존 리뷰를 수정합니다.
   *
   * @param id 수정할 리뷰의 ID.
   * @param updatedReview 수정할 리뷰 정보를 포함한 객체.
   * @return 수정된 리뷰 객체를 포함하는 ApiResponse.
   */
  @PutMapping("/{id}")
  public ApiResponse<Review> updateReview(@PathVariable Long id, @RequestBody Review updatedReview) {
    return ApiResponse.ok(reviewService.updateReview(id, updatedReview));
  }



  /**
   * 특정 리뷰를 삭제합니다.
   *
   * @param id 삭제할 리뷰의 ID.
   * @return 삭제 성공 메시지를 포함하는 ApiResponse.
   */
  @DeleteMapping("/{id}")
  public ApiResponse<Void> deleteReview(@PathVariable Long id) {
    reviewService.deleteReview(id);
    return ApiResponse.ok("Review deleted successfully.");
  }
}
