package com.example.fastcampusmysql.domain.follow.service;

import com.example.fastcampusmysql.domain.follow.entity.Follow;
import com.example.fastcampusmysql.domain.follow.repository.FollowRepository;
import com.example.fastcampusmysql.domain.member.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@RequiredArgsConstructor
@Service
public class FollowWriteService {
    //서로다른 도메인에 데이터를 주고 받아야 할때 어디서해야 하는가? 헥사고날 아키텍처, DDD도있고, Layerd 아키텍처
    //레포지토리나 서비스를 주입받으면 결합이 심해지고, 구체적인 구현에 의존하게되어서 id를 받지않음.
    final private FollowRepository followRepository;

    public void create(MemberDto fromMember, MemberDto toMember) {
        /*
            from, to 회원정보를 받아서
            저장할텐데..
            from <-> to validate
         */
        Assert.isTrue(!fromMember.id().equals(toMember.id()), "From, To 회원이 동일합니다.");

        var follow = Follow.builder()
                .fromMemberId(fromMember.id())
                .toMemberId(toMember.id())
                .build();

        followRepository.save(follow);
    }
}
