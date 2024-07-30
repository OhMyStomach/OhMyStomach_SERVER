package org.ohmystomach.ohmystomach_server.toilet.application;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.ohmystomach.ohmystomach_server.toilet.dao.ToiletRepository;
import org.ohmystomach.ohmystomach_server.toilet.domain.Toilet;
import org.springframework.stereotype.Service;

/**
 * 공중화장실 관련 비즈니스 로직을 처리하는 서비스 클래스.
 */
@Service
@RequiredArgsConstructor
public class ToiletService {

  // ToiletRepository 의존성 주입, 데이터베이스와의 상호작용을 처리
  private final ToiletRepository toiletRepository;

  /**
   * 모든 공중화장실 데이터를 반환
   *
   * @return 모든 Toilet 객체의 리스트.
   */
  public List<Toilet> getAllToilets() {
    // 모든 공중화장실 데이터를 데이터베이스에서 조회하여 반환
    return toiletRepository.findAll();
  }

  /**
   * 특정 ID에 해당하는 공중화장실 데이터를 반환
   *
   * @param id 조회할 공중화장실의 ID.
   * @return 해당 ID의 Toilet 객체.
   * @throws RuntimeException 해당 ID로 공중화장실을 찾을 수 없을 때 예외 발생.
   */
  public Toilet getToiletById(Long id) {
    // ID로 공중화장실을 조회, 없을 경우 예외 발생
    return toiletRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Toilet not found with id " + id));
  }
}
