package com.springboot.relationship.data.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@MappedSuperclass //JPA의 엔티티 클래스가 상속받을 경우 자식클래스에게 매핑정보 전달
//엔티티를 데이터베이스에 적용하기 전후로 콜백을 요청하는 어노테이션
@EntityListeners(AuditingEntityListener.class) //엔티티 Auditing정볼르 주입하는 JPA엔티티 리스터클래스
public class BaseEntity {

    @CreatedDate //데이터 생성날짜 자동으로 주입
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate //데이터 수정날짜 자동으로 주입
    private LocalDateTime updatedAt;
}