package org.ohmystomach.ohmystomach_server.domain.smoke.application;

import lombok.RequiredArgsConstructor;
import org.ohmystomach.ohmystomach_server.domain.smoke.dao.SmokeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class SmokeService {
    private final SmokeRepository smokeRepository;
}
