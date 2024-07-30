package org.ohmystomach.ohmystomach_server.review.application;

import lombok.RequiredArgsConstructor;
import org.ohmystomach.ohmystomach_server.review.dao.ReviewRepository;
import org.ohmystomach.ohmystomach_server.review.domain.Review;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 리뷰 관련 비즈니스 로직을 처리하는 서비스 클래스.
 */
@Service
@RequiredArgsConstructor
public class ReviewService {

  // ReviewRepository 의존성 주입, 리뷰 데이터베이스 작업 처리
  private final ReviewRepository reviewRepository;




  /**
   * 특정 화장실의 리뷰 목록을 조회합니다.
   *
   * @param toiletId 조회할 화장실의 ID.
   * @param sort 정렬 기준 (ratingDesc: 별점 높은 순, ratingAsc: 별점 낮은 순, default: 최신순).
   * @return 정렬 기준에 따라 정렬된 리뷰 리스트.
   */
  public List<Review> getReviewsByToiletId(Long toiletId, String sort) {
    if ("ratingDesc".equalsIgnoreCase(sort)) {
      return reviewRepository.findByToiletIdOrderByRatingDesc(toiletId);
    } else if ("ratingAsc".equalsIgnoreCase(sort)) {
      return reviewRepository.findByToiletIdOrderByRatingAsc(toiletId);
    } else {
      return reviewRepository.findByToiletIdOrderByCreatedDateDesc(toiletId);
    }
  }




  /**
   * 새로운 리뷰를 추가합니다.
   *
   * @param review 추가할 리뷰 객체.
   * @return 저장된 리뷰 객체.
   */
  public Review addReview(Review review) {
    return reviewRepository.save(review);
  }




  /**
   * 기존 리뷰를 수정합니다.
   *
   * @param id 수정할 리뷰의 ID.
   * @param updatedReview 수정할 리뷰 정보를 포함한 객체.
   * @return 수정된 리뷰 객체.
   * @throws RuntimeException 리뷰를 찾을 수 없을 경우 예외 발생.
   */
  public Review updateReview(Long id, Review updatedReview) {
    Review review = reviewRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Review not found with id " + id));
    review.setUsername(updatedReview.getUsername());
    review.setContent(updatedReview.getContent());
    review.setRating(updatedReview.getRating());
    return reviewRepository.save(review);
  }




  /**
   * 특정 리뷰를 삭제합니다.
   *
   * @param id 삭제할 리뷰의 ID.
   */
  public void deleteReview(Long id) {
    reviewRepository.deleteById(id);
  }
}
