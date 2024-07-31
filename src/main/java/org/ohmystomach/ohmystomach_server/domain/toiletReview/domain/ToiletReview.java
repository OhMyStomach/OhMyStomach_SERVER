package org.ohmystomach.ohmystomach_server.domain.toiletReview.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ohmystomach.ohmystomach_server.domain.toiletReview.dto.request.UpdateToiletReviewServiceRequestDto;
import org.ohmystomach.ohmystomach_server.domain.toilet.domain.Toilet;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class ToiletReview {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String username;  // 작성자 이름
  private String content;  // 후기 내용
  private int rating;  // 별점 (1~5)

  private LocalDateTime createdDate = LocalDateTime.now();  // 생성일자

  @ManyToOne // 여러 개의 Review가 하나의 Toilet에 연결될 수 있음을 의미
  @JoinColumn(name = "toilet_id") //  Review 테이블에서 toilet_id 컬럼은 Toilet 테이블의 기본 키를 참조, JPA가 자동으로 설정
  private Toilet toilet;  // 후기 대상 화장실

  @Builder
  public ToiletReview(String username, String content, int rating, Toilet toilet) {
    this.username = username;
    this.content = content;
    this.rating = rating;
    this.toilet = toilet;
  }

  public void update(UpdateToiletReviewServiceRequestDto dto) {
    this.username = dto.username();
    this.content = dto.content();
    this.rating = dto.rating();
  }
}