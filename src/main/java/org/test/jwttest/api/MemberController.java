package org.test.jwttest.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.test.jwttest.domain.Member;
import org.test.jwttest.service.MemberService;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/member")
@RestController
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/list")
    public ResponseEntity<List<Member>> member(){
        return ResponseEntity.ok().body(memberService.getMembers());
    }
}
