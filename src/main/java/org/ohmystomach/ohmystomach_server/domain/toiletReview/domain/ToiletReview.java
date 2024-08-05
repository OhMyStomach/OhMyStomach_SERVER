package org.ohmystomach.ohmystomach_server.domain.toiletReview.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ohmystomach.ohmystomach_server.domain.toiletReview.dto.request.UpdateToiletReviewServiceRequestDto;
import org.ohmystomach.ohmystomach_server.domain.toilet.domain.Toilet;
import org.ohmystomach.ohmystomach_server.domain.user.domain.User;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class ToiletReview {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String content;  // 후기 내용
  private int rating;  // 별점 (1~5)

  private LocalDateTime createdDate = LocalDateTime.now();  // 생성일자

  @ManyToOne // 여러 개의 Review가 하나의 Toilet에 연결될 수 있음을 의미
  @JoinColumn(name = "toilet_id") //  Review 테이블에서 toilet_id 컬럼은 Toilet 테이블의 기본 키를 참조, JPA가 자동으로 설정
  private Toilet toilet;  // 후기 대상 화장실

  @ManyToOne
  @JoinColumn(name = "userId", nullable = false)
  @JsonBackReference
  private User user;

  @Builder
  public ToiletReview(User user, String content, int rating, Toilet toilet) {
    this.user = user;
    this.content = content;
    this.rating = rating;
    this.toilet = toilet;
  }

  public void update(UpdateToiletReviewServiceRequestDto dto) {
    this.content = dto.content();
    this.rating = dto.rating();
  }
}