package org.ohmystomach.ohmystomach_server.smokemyplace.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.ohmystomach.ohmystomach_server.smokemyplace.domain.UserSmoke;

import java.util.List;

@Repository
public interface UserSmokeRepository extends JpaRepository<UserSmoke, Long> {
    List<UserSmoke> findByUserId(Long userId);
}
