package org.ohmystomach.ohmystomach_server.domain.smoke.application;

import lombok.RequiredArgsConstructor;
import org.ohmystomach.ohmystomach_server.domain.smoke.dao.SmokeRepository;
import org.ohmystomach.ohmystomach_server.domain.smoke.domain.Smoke;
import org.ohmystomach.ohmystomach_server.global.common.response.ApiResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class SmokeService {

    private final SmokeRepository smokeRepository;

    public ApiResponse<List<Smoke>> retrieveAllSmoke() {
        List<Smoke> smokeList = smokeRepository.findAll();
        if(smokeList.isEmpty()) {
            return ApiResponse.ok("흡연구역이 존재하지 않습니다.");
        }
        return ApiResponse.ok("흡연구역 목록을 성공적으로 조회했습니다.", smokeList);
    }
}
