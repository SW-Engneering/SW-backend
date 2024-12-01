package com.sparta.sw_backend_team.repository;

import com.sparta.sw_backend_team.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Integer> {
    // 특정 사용자 ID로 알림 검색
    List<Notification> findByUserId(int userId);
}
