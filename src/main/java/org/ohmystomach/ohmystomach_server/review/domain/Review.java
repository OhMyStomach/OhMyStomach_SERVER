package org.ohmystomach.ohmystomach_server.review.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.ohmystomach.ohmystomach_server.toilet.domain.Toilet;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Review {

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
}