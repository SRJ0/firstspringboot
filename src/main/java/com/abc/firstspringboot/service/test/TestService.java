package com.abc.firstspringboot.service.test;

import com.abc.firstspringboot.domain.member.Member;
import com.abc.firstspringboot.domain.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestService {
    @Autowired
    MemberRepository memberRepository;

    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

}
