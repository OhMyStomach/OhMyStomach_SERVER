package org.ohmystomach.ohmystomach_server.toiletmyplace.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.ohmystomach.ohmystomach_server.toiletmyplace.domain.UserToilet;

import java.util.List;

@Repository
public interface UserToiletRepository extends JpaRepository<UserToilet, Long> {
    List<UserToilet> findByUserId(Long userId);
}
