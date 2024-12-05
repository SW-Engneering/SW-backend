package com.swengineer.sportsmatch.repository;

import com.swengineer.sportsmatch.entity.InquiryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InquiryRepository extends JpaRepository<InquiryEntity, Long> {
    List<InquiryEntity> findByUserEntityUserId(int userId);
}
