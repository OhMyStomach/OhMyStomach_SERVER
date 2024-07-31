package org.ohmystomach.ohmystomach_server.domain.toilet.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Toilet {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;  // 이름
  private String category;  // 구분
  private Double wsg84x;  // WSG84X
  private Double wsg84y;  // WSG84Y
}