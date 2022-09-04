package com.sto.salepurchase.backstosalepurchase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RequestMapping("/member")
@RestController
public class MemberAPIController {
    @Autowired
    private MemberRepository memberRepository;

    @RequestMapping(value = "/select", method = {RequestMethod.GET, RequestMethod.POST})
    public List<Member> selectAll() {
        return memberRepository.findAll();
    }

    @RequestMapping(value = "/insert", method = {RequestMethod.GET, RequestMethod.POST}, produces = "application/json; charset=utf-8")
    public Member insert(@RequestBody Map<String, String> map) {
        return memberRepository.save(new Member(map.get("id"), map.get("name"), map.get("password")));
    }
    
}
