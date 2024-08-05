package org.ohmystomach.ohmystomach_server.domain.smokeReview.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ohmystomach.ohmystomach_server.domain.smoke.domain.Smoke;
import org.ohmystomach.ohmystomach_server.domain.smokeReview.dto.request.UpdateSmokeReviewServiceRequestDto;
import org.ohmystomach.ohmystomach_server.domain.user.domain.User;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class SmokeReview {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String username;  // 작성자 이름
  private String content;  // 후기 내용
  private int rating;  // 별점 (1~5)

  private LocalDateTime createdDate = LocalDateTime.now();  // 생성일자

  @ManyToOne // 여러 개의 Review가 하나의 Toilet에 연결될 수 있음을 의미
  @JoinColumn(name = "smoke_id") //  Review 테이블에서 toilet_id 컬럼은 Toilet 테이블의 기본 키를 참조, JPA가 자동으로 설정
  private Smoke smoke;  // 후기 대상 화장실

  @ManyToOne
  @JoinColumn(name = "userId", nullable = false)
  @JsonBackReference
  private User user;

  @Builder
  public SmokeReview(String username, String content, int rating, Smoke smoke) {
    this.username = username;
    this.content = content;
    this.rating = rating;
    this.smoke = smoke;
  }

  public void update(UpdateSmokeReviewServiceRequestDto dto) {
    this.username = dto.username();
    this.content = dto.content();
    this.rating = dto.rating();
  }
}