package org.ohmystomach.ohmystomach_server.domain.smokemyplace.dao;

import org.ohmystomach.ohmystomach_server.domain.smokemyplace.domain.UserSmoke;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserSmokeRepository extends JpaRepository<UserSmoke, Long> {
    List<UserSmoke> findByUserId(Long userId);
}
