package com.swengineer.sportsmatch.repository;

import com.swengineer.sportsmatch.entity.AnnouncementEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnnouncementRepository extends JpaRepository<AnnouncementEntity, Integer> {
    List<AnnouncementEntity> findByTeam_TeamId(int teamId);
}
