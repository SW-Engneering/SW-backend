package com.swengineer.sportsmatch.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class BaseEntity {
    @CreationTimestamp //생성 시간
    @Column(updatable = false) //수정될때 변동 x
    private LocalDateTime post_created_time; //작성일

    @UpdateTimestamp // 수정 시간
    @Column(insertable = false) //생성될때 변동 x
    private LocalDateTime post_updated_time; // 수정일

    @CreationTimestamp //생성 시간
    @Column(updatable = false) //수정될때 변동 x
    private LocalDateTime registDate; //회원가입일
}
