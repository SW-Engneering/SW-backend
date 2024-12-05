package com.swengineer.sportsmatch.dto;

import com.swengineer.sportsmatch.entity.InquiryEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InquiryDTO {

    private Long inquiryId;  // 문의 ID
    private int userId;  // 회원 ID
    private String inquiryTitle;  // 문의 제목
    private String inquiryContent;  // 문의 내용
    private boolean responseExists;  // 답변 유무
    private String responseResult;  // 답변 결과

    // Entity -> DTO 변환 메서드
    public static InquiryDTO toInquiryDTO(InquiryEntity inquiryEntity) {
        InquiryDTO inquiryDTO = new InquiryDTO();
        inquiryDTO.setInquiryId(inquiryEntity.getInquiryId());
        inquiryDTO.setUserId(inquiryEntity.getUserEntity().getUserId());
        inquiryDTO.setInquiryTitle(inquiryEntity.getInquiryTitle());
        inquiryDTO.setInquiryContent(inquiryEntity.getInquiryContent());
        inquiryDTO.setResponseExists(inquiryEntity.isResponseExists());
        inquiryDTO.setResponseResult(inquiryEntity.getResponseResult());
        return inquiryDTO;
    }
}
