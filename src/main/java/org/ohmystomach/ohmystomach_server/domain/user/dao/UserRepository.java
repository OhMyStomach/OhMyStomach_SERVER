package org.ohmystomach.ohmystomach_server.domain.user.dao;

import org.ohmystomach.ohmystomach_server.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, String> {
    List<User> findByNickname(String nickname);
}
