package org.ohmystomach.ohmystomach_server.domain.smokemyplace.dao;

import org.ohmystomach.ohmystomach_server.domain.smokemyplace.domain.UserSmoke;
import org.ohmystomach.ohmystomach_server.domain.toiletmyplace.domain.UserToilet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserSmokeRepository extends JpaRepository<UserSmoke, Long> {
    List<UserSmoke> findByUserId(String userId);
    boolean existsByIdAndUserId(Long id, String userId);
    Optional<UserSmoke> findByIdAndUserId(Long id, String userId);
}
