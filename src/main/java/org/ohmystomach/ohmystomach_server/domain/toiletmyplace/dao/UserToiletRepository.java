package org.ohmystomach.ohmystomach_server.domain.toiletmyplace.dao;

import org.ohmystomach.ohmystomach_server.domain.toiletmyplace.domain.UserToilet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserToiletRepository extends JpaRepository<UserToilet, Long> {
    List<UserToilet> findByUserId(String userId);
    boolean existsByIdAndUserId(Long id, String userId);
    Optional<UserToilet> findByIdAndUserId(Long id, String userId);
}
