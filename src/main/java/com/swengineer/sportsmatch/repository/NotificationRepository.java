package com.swengineer.sportsmatch.repository;

import com.swengineer.sportsmatch.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Integer> {
}
