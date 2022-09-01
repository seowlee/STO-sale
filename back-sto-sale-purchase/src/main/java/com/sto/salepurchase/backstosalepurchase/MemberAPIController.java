package com.sto.salepurchase.backstosalepurchase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/member")
@RestController
public class MemberAPIController {
    @Autowired
    private MemberRepository memberRepository;

    @RequestMapping(value = "/select", method = {RequestMethod.GET, RequestMethod.POST})
    public List<Member> selectAll() {
        return memberRepository.findAll();
    }
    
}
