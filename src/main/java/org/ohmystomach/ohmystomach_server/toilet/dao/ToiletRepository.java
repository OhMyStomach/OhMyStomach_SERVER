package org.ohmystomach.ohmystomach_server.toilet.dao;

import org.ohmystomach.ohmystomach_server.toilet.domain.Toilet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToiletRepository extends JpaRepository<Toilet, Long> {
}