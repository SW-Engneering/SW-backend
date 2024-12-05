package com.swengineer.sportsmatch.service;

import com.swengineer.sportsmatch.dto.InquiryDTO;
import com.swengineer.sportsmatch.entity.InquiryEntity;
import com.swengineer.sportsmatch.repository.InquiryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InquiryService {

    @Autowired
    private InquiryRepository inquiryRepository;

    // 문의 작성 메서드
    public void createInquiry(InquiryDTO inquiryDTO) {
        InquiryEntity inquiryEntity = new InquiryEntity();
        inquiryEntity.setInquiryTitle(inquiryDTO.getInquiryTitle());
        inquiryEntity.setInquiryContent(inquiryDTO.getInquiryContent());
        inquiryEntity.setResponseExists(false);  // 초기 상태
        inquiryEntity.setResponseResult(null);  // 초기 상태
        inquiryRepository.save(inquiryEntity);
    }

    // 특정 사용자의 모든 문의 내역을 InquiryDTO로 반환
    public List<InquiryDTO> getUserInquiries(int userId) {
        List<InquiryEntity> inquiries = inquiryRepository.findByUserEntityUserId(userId);
        return inquiries.stream()
                .map(InquiryDTO::toInquiryDTO)
                .collect(Collectors.toList());
    }

    // 특정 사용자의 문의 삭제
    public void deleteInquiry(Long inquiryId, int userId) {
        InquiryEntity inquiry = inquiryRepository.findById(inquiryId)
                .orElseThrow(() -> new RuntimeException("Inquiry not found"));

        if (inquiry.getUserEntity().getUserId() != userId) {
            throw new RuntimeException("You do not have permission to delete this inquiry");
        }

        inquiryRepository.delete(inquiry);
    }
}
