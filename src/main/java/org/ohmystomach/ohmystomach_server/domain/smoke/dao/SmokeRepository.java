package org.ohmystomach.ohmystomach_server.domain.smoke.dao;

import org.ohmystomach.ohmystomach_server.domain.smoke.domain.Smoke;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SmokeRepository extends JpaRepository<Smoke, Long> {
}
