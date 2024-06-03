package com.example.fastcampusmysql.application.usecase;

import com.example.fastcampusmysql.domain.follow.service.FollowWriteService;
import com.example.fastcampusmysql.domain.member.service.MemberReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CreateFollowMemberUsecase {
    //read, write를 잘 분리해서 책임이 분리됨, 의존성의 분리
    final private MemberReadService memberReadService;
    final private FollowWriteService followWriteService;

    //usecase layer는 가능한 로직이없어야한다.
    //각 비즈니스 로직은 각 서비스에 있어야하며, usecase layer는 흐름을 제어해야 한다.
    public void execute(Long fromMemberId, Long toMemberId) {
        /*
            1. 입력받은 memberId로 회원조회
            2. FollowWriteService.create()
         */
        var fromMember = memberReadService.getMember(fromMemberId);
        var toMember = memberReadService.getMember(toMemberId);

        followWriteService.create(fromMember, toMember);
    }
}
