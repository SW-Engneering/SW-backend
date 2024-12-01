package com.sparta.sw_backend_team.repository;

import com.sparta.sw_backend_team.entity.Inquiry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InquiryRepository extends JpaRepository<Inquiry, Integer> {
    // 필요 시 추가 메서드 작성
}
