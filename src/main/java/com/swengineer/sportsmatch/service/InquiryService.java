package com.swengineer.sportsmatch.service;

import com.swengineer.sportsmatch.dto.InquiryDTO;
import com.swengineer.sportsmatch.entity.InquiryEntity;
import com.swengineer.sportsmatch.entity.UserEntity;
import com.swengineer.sportsmatch.repository.InquiryRepository;
import com.swengineer.sportsmatch.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Transactional
public class InquiryService {

    @Autowired
    private InquiryRepository inquiryRepository;

    @Autowired
    private UserRepository userRepository;

    // 문의 작성
    public void createInquiry(InquiryDTO inquiryDTO) {
        UserEntity user = userRepository.findById(inquiryDTO.getUserId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "사용자를 찾을 수 없습니다."));

        InquiryEntity inquiry = new InquiryEntity();
        inquiry.setUserEntity(user);
        inquiry.setInquiryTitle(inquiryDTO.getInquiryTitle());
        inquiry.setInquiryContent(inquiryDTO.getInquiryContent());
        inquiryRepository.save(inquiry);
    }

    // 사용자의 문의 내역 조회
    public List<InquiryDTO> getUserInquiries(int userId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "사용자를 찾을 수 없습니다."));

        return inquiryRepository.findByUserEntity(user).stream()
                .map(InquiryDTO::toInquiryDTO)
                .toList();
    }

    // 문의 삭제
    public void deleteInquiry(Long inquiryId, int userId) {
        InquiryEntity inquiry = inquiryRepository.findById(inquiryId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "문의 내용을 찾을 수 없습니다."));

        if (inquiry.getUserEntity().getUserId() != userId) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "삭제 권한이 없습니다.");
        }

        inquiryRepository.delete(inquiry);
    }
}
