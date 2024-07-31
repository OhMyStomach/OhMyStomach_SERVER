package org.ohmystomach.ohmystomach_server.domain.toilet.application;

import lombok.RequiredArgsConstructor;
import org.ohmystomach.ohmystomach_server.global.common.response.ApiResponse;
import org.ohmystomach.ohmystomach_server.domain.toilet.dao.ToiletRepository;
import org.ohmystomach.ohmystomach_server.domain.toilet.domain.Toilet;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ToiletService {

  // ToiletRepository 의존성 주입, 데이터베이스와의 상호작용을 처리
  private final ToiletRepository toiletRepository;

  public ApiResponse<List<Toilet>> retrieveAllToilets() {
    // 모든 공중화장실 데이터를 데이터베이스에서 조회하여 반환
    List<Toilet> toiletList = toiletRepository.findAll();
    if(toiletList.isEmpty()) {
      return ApiResponse.ok("공중화장실이 존재하지 않습니다.");
    }
    return ApiResponse.ok("공중화장실 목록을 성공적으로 조회했습니다.", toiletList);
  }

  public ApiResponse<Toilet> retrieveToiletById(Long id) {
    // ID로 공중화장실을 조회, 없을 경우 예외 발생
    Optional<Toilet> optionalToilet = toiletRepository.findById(id);
    if(optionalToilet.isEmpty()){
      return ApiResponse.ok("공중화장실 데이터가 존재하지 않습니다.");
    }
    Toilet toilet = optionalToilet.get();
    return ApiResponse.ok("공중화장실 데이터를 성공적으로 조회했습니다.", toilet);
  }
}
