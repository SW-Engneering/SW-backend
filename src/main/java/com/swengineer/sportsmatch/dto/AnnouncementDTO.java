package com.swengineer.sportsmatch.dto;

import com.swengineer.sportsmatch.entity.AnnouncementEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class AnnouncementDTO {
    private int announcementId;    // 공지사항 ID
    private String title;          // 공지 제목
    private String content;        // 공지 내용
    private LocalDateTime createdAt; // 작성일자


    public static AnnouncementDTO toAnnouncementDTO(AnnouncementEntity announcement) {
        AnnouncementDTO dto = new AnnouncementDTO();
        dto.setAnnouncementId(announcement.getAnnouncementId());
        dto.setTitle(announcement.getTitle());
        dto.setContent(announcement.getContent());
        dto.setCreatedAt(announcement.getCreatedAt());
        return dto;
    }

}
