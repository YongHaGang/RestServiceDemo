package com.example.restservice.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import com.example.restservice.entity.Member;
import com.example.restservice.entity.Test;
import com.example.restservice.exception.TestException;
import com.example.restservice.repository.MemberRepository;
import com.example.restservice.service.MemberService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestRestController {

    @Autowired
    MemberService memberService;

/*
    @GetMapping(value = "/member/{id}")
    public Member getMember(@PathVariable Long id) {
        return memberService.getMemberById(id);
    }*/

    @GetMapping(value = "/test/{id}")
    public ResponseEntity<Test> getTestById(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<Test>(memberService.getTestById(id), HttpStatus.ACCEPTED);
    }

    @GetMapping(value = "/member-all")
    public List<Member> getMemberAll() {
        return memberService.getMemberAll();
    }

    @GetMapping(value = "/member-id")
    public Member getMemberById(@RequestParam Long id) {
        Member member = memberService.getMemberById(id);
        return member;
    }

    //@Transactional
    @GetMapping(value = "/member-name")
    public List<Member> getMemberByName(@RequestParam String name) {
        List<Member> list = memberService.getMemberByNameContaining(name);

        return list;
    }

    @PostMapping(value = "/member")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public Member setMember(@RequestParam String name, @RequestParam int age) {
        Member newMember = new Member();
        newMember.setAge(age);
        newMember.setName(name);
        return memberService.addMember(newMember);
    }

    @DeleteMapping(value = "/member")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public Member deleteMember(@RequestParam long id) {
        return memberService.deleteMember(id);
    }

    @PostMapping(value = "/memberbad")
    @ResponseStatus(code = HttpStatus.CREATED)
    public String setMemberBad() {
        throw new TestException(new RuntimeException("test"));
    }

    @ExceptionHandler
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public String handling(Exception e) {
        return "{\"message\":\"" + e.getMessage() + "\"}";
    }
    @ExceptionHandler(TestException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public String handlingTestException(Exception e) {
        return "{\"TestException message\":\"" + e.getMessage() + "\"}";
    }
}
