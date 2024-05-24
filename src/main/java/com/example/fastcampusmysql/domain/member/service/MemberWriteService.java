package com.example.fastcampusmysql.domain.member.service;

import com.example.fastcampusmysql.domain.member.dto.RegisterMemberCommand;
import com.example.fastcampusmysql.domain.member.entity.Member;
import com.example.fastcampusmysql.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberWriteService {
    final private MemberRepository memberRepository;
    public Member create(RegisterMemberCommand command) {
        /*
            GOAL - 회원정보(이메일, 닉네임, 생년월일)를 등록한다.
                 - 닉네임은 10자를 넘길 수 없다.
            파라미터 - memberRegisterCommand

            val member = Member.of(memberRegisterCommand)
            memberRepository.save(member)
            memberRepository.save(member)
         */
        var member = Member.builder()
                .nickname(command.nickname())
                .birthDay(command.birthday())
                .email(command.email())
                .build();
        return memberRepository.save(member);
    }
}
