package com.example.fastcampusmysql.domain.member.service;

import com.example.fastcampusmysql.domain.member.dto.RegisterMemberCommand;
import com.example.fastcampusmysql.domain.member.entity.Member;
import com.example.fastcampusmysql.domain.member.entity.MemberNicknameHistory;
import com.example.fastcampusmysql.domain.member.repository.MemberNicknameHistoryRepository;
import com.example.fastcampusmysql.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

@RequiredArgsConstructor
@Service
public class MemberWriteService {
    final private MemberRepository memberRepository;

    final private MemberNicknameHistoryRepository memberNicknameHistoryRepository;

    //@Transactional
    public Member create(RegisterMemberCommand command) {
        /*
            GOAL - 회원정보(이메일, 닉네임, 생년월일)를 등록한다.
                 - 닉네임은 10자를 넘길 수 없다.
            파라미터 - memberRegisterCommand

            val member = Member.of(memberRegisterCommand)
            memberRepository.save(member)
            memberRepository.save(member)
         */
        //inner함수 getMember를 호출시 transactional이 제대로 동작하지않음 (프록시패턴)
        return getMember(command);
    }

    @Transactional
    private Member getMember(RegisterMemberCommand command) {
        var member = Member.builder()
                .nickname(command.nickname())
                .birthday(command.birthday())
                .email(command.email())
                .build();

        //TransactionTemplate

        var savedMember = memberRepository.save(member);
        var zeros = 0 / 0;

        saveMemberNicknameHistory(savedMember);
        return savedMember;
    }


    public void changeNickname(Long memberId, String nickname) {
        /**
         * 1. 회원의 이름을 변경
         * 2. 변경내역을 저장한다.
         */
        var member = memberRepository.findById(memberId).orElseThrow();
        member.changeNickname(nickname);
        memberRepository.save(member);

        saveMemberNicknameHistory(member);
    }

    private void saveMemberNicknameHistory(Member member) {
        var history = MemberNicknameHistory
                .builder()
                .memberId(member.getId())
                .nickname(member.getNickname())
                .build();

        memberNicknameHistoryRepository.save(history);
    }

}
