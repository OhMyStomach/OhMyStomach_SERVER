package org.ohmystomach.ohmystomach_server.domain.smokeReview.dao;

import org.ohmystomach.ohmystomach_server.domain.smokeReview.domain.SmokeReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 리뷰 엔티티와 데이터베이스 간의 상호작용을 담당하는 리포지토리 인터페이스.
 */
@Repository
public interface SmokeReviewRepository extends JpaRepository<SmokeReview, Long> {



  /**
   * 특정 흡연구역에 대한 리뷰를 생성일자 기준으로 내림차순 정렬하여 반환합니다.
   *
   * @param smokeId 조회할 흡연구역의 ID.
   * @return 생성일자 내림차순으로 정렬된 리뷰 리스트.
   */
  List<SmokeReview> findBySmokeIdOrderByCreatedDateDesc(Long smokeId);





  /**
   * 특정 흡연구역에 대한 리뷰를 별점 높은 순으로 정렬하여 반환합니다.
   *
   * @param smokeId 조회할 흡연구역의 ID.
   * @return 별점 높은 순으로 정렬된 리뷰 리스트.
   */
  List<SmokeReview> findBySmokeIdOrderByRatingDesc(Long smokeId);






  /**
   * 특정 흡연구역에 대한 리뷰를 별점 낮은 순으로 정렬하여 반환합니다.
   *
   * @param smokeId 조회할 흡연구역의 ID.
   * @return 별점 낮은 순으로 정렬된 리뷰 리스트.
   */
  List<SmokeReview> findBySmokeIdOrderByRatingAsc(Long smokeId);

  boolean existsByIdAndUserId(Long id, String userId);
}
