package com.example.fastcampusmysql.controller;


import com.example.fastcampusmysql.domain.follow.application.usecase.CreateFollowMemberUsecase;
import com.example.fastcampusmysql.domain.follow.application.usecase.GetFollowingMemberUsercase;
import com.example.fastcampusmysql.domain.member.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/follow")
@RestController
public class FollowController {
    final private CreateFollowMemberUsecase createFollowMemberUsecase;
    final private GetFollowingMemberUsercase getFollowingMemberUsercase;

    @PostMapping("/{fromId}/{toId}")
    public void create(@PathVariable Long fromId, @PathVariable Long toId) {
        createFollowMemberUsecase.execute(fromId, toId);
    }

    @GetMapping("/members/{fromId}")
    public List<MemberDto> create(@PathVariable Long fromId) {
        return getFollowingMemberUsercase.execute(fromId);
    }
}
