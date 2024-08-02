package org.ohmystomach.ohmystomach_server.domain.toiletReview.application;

import lombok.RequiredArgsConstructor;
import org.ohmystomach.ohmystomach_server.domain.toiletReview.dao.ToiletReviewRepository;
import org.ohmystomach.ohmystomach_server.global.common.response.ApiResponse;
import org.ohmystomach.ohmystomach_server.global.error.ErrorCode;
import org.ohmystomach.ohmystomach_server.domain.toiletReview.domain.ToiletReview;
import org.ohmystomach.ohmystomach_server.domain.toiletReview.dto.request.CreateToiletReviewServiceRequestDto;
import org.ohmystomach.ohmystomach_server.domain.toiletReview.dto.request.UpdateToiletReviewServiceRequestDto;
import org.ohmystomach.ohmystomach_server.domain.toilet.application.ToiletService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * 리뷰 관련 비즈니스 로직을 처리하는 서비스 클래스.
 */
@Service
@Transactional
@RequiredArgsConstructor
public class ToiletReviewService {

  // ReviewRepository 의존성 주입, 리뷰 데이터베이스 작업 처리
  private final ToiletReviewRepository reviewRepository;
  private final ToiletService toiletService;

  /**
   * 특정 화장실의 리뷰 목록을 조회합니다.
   *
   * @param toiletId 조회할 화장실의 ID.
   * @param sort 정렬 기준 (ratingDesc: 별점 높은 순, ratingAsc: 별점 낮은 순, default: 최신순).
   * @return 정렬 기준에 따라 정렬된 리뷰 리스트.
   */
  public ApiResponse<List<ToiletReview>> getReviewsByToiletId(Long toiletId, String sort) {
    List<ToiletReview> reviewList;
    if ("ratingDesc".equalsIgnoreCase(sort)) {
      reviewList = reviewRepository.findByToiletIdOrderByRatingDesc(toiletId);
    } else if ("ratingAsc".equalsIgnoreCase(sort)) {
      reviewList = reviewRepository.findByToiletIdOrderByRatingAsc(toiletId);
    } else {
      reviewList = reviewRepository.findByToiletIdOrderByCreatedDateDesc(toiletId);
    }
    if(reviewList.isEmpty()) {
      return ApiResponse.ok("후기가 존재하지 않습니다.");
    }
    return ApiResponse.ok("공중화장실 후기 목록을 성공적으로 조회했습니다.", reviewList);
  }




  /**
   * 새로운 리뷰를 추가합니다.
   *
   * @param dto 추가할 리뷰 객체.
   * @return 저장된 리뷰 객체.
   */
  public ApiResponse<ToiletReview> addReview(CreateToiletReviewServiceRequestDto dto) {
    ToiletReview savedReview = reviewRepository.save(dto.toEntity(toiletService.retrieveToiletById(dto.toiletId()).getData()));
    return ApiResponse.ok("후기 등록을 완료했습니다.", savedReview);
  }




  /**
   * 기존 리뷰를 수정합니다.
   *
   * @param id 수정할 리뷰의 ID.
   * @param dto 수정할 리뷰 정보를 포함한 객체.
   * @return 수정된 리뷰 객체.
   * @throws RuntimeException 리뷰를 찾을 수 없을 경우 예외 발생.
   */
  public ApiResponse<ToiletReview> updateReview(Long id, UpdateToiletReviewServiceRequestDto dto) {
    Optional<ToiletReview> optionalReview = reviewRepository.findById(id);

    if(optionalReview.isEmpty()) {
      return ApiResponse.withError(ErrorCode.INVALID_REVIEW_ID);
    }

    ToiletReview review = optionalReview.get();
    review.update(dto);
    ToiletReview updatedReview = reviewRepository.save(review);
    return ApiResponse.ok("후기를 성공적으로 수정했습니다.", updatedReview);
  }




  /**
   * 특정 리뷰를 삭제합니다.
   *
   * @param id 삭제할 리뷰의 ID.
   */
  public ApiResponse<String> deleteReview(Long id) {
    if(!reviewRepository.existsById(id)){
      return ApiResponse.withError(ErrorCode.INVALID_REVIEW_ID);
    }
    reviewRepository.deleteById(id);
    return ApiResponse.ok("후기를 성공적으로 삭제했습니다.");
  }
}
