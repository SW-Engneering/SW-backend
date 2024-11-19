package com.swengineer.sportsmatch.repository;

import com.swengineer.sportsmatch.entity.Inquiry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InquiryRepository extends JpaRepository<Inquiry, Integer> {
}
