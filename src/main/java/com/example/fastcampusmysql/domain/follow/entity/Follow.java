package com.example.fastcampusmysql.domain.follow.entity;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
public class Follow {
    /** join은 가능한 미루는게 좋다. follow와 member의 강결합이 이루어짐
     *  프로젝트 초기부터 강결합이 이루어지면 유연상있는 설계가 되기 힘듬
     *  아키텍처 적으로 리팩토링하면서 성능해결이 힘들어진다.
     */
    final private Long id;
    final private Long fromMemberId;
    final private Long toMemberId;
    final private LocalDateTime createdAt;

    @Builder
    public Follow(Long id, Long fromMemberId, Long toMemberId, LocalDateTime createdAt) {
        this.id = id;
        this.fromMemberId = Objects.requireNonNull(fromMemberId);
        this.toMemberId = Objects.requireNonNull(toMemberId);
        this.createdAt = createdAt == null ? LocalDateTime.now() : createdAt;
    }
}
