package org.ohmystomach.ohmystomach_server.domain.toilet.dao;

import org.ohmystomach.ohmystomach_server.domain.toilet.domain.Toilet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToiletRepository extends JpaRepository<Toilet, Long> {
}