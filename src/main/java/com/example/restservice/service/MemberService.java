package com.example.restservice.service;

import java.util.List;

import com.example.restservice.entity.Item;
import com.example.restservice.entity.Member;
import com.example.restservice.entity.MemberItem;
import com.example.restservice.entity.Pet;
import com.example.restservice.entity.Test;
import com.example.restservice.entity.Item.ItemBuilder;
import com.example.restservice.entity.Member.MemberBuilder;
import com.example.restservice.entity.MemberItem.MemberItemBuilder;
import com.example.restservice.entity.Pet.PetBuilder;
import com.example.restservice.repository.ItemRepository;
import com.example.restservice.repository.MemberItemRepository;
import com.example.restservice.repository.MemberRepository;
import com.example.restservice.repository.PetRepository;
import com.example.restservice.repository.TestRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;
    
    @Autowired
    private PetRepository petRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private MemberItemRepository memberItemRepository;

    @Autowired
    private TestRepository testRepository;

    public Test getTestById(Long id) {
        return testRepository.findById(id).get();
    }

    public List<Member> getMemberAll() {
        return memberRepository.findAll();
    }

    public Member addMember(Member newMember) {
        memberRepository.save(newMember);
        return newMember;
    }

    public Member deleteMember(Long id) {
        Member member = memberRepository.findById(id).get();
        memberRepository.delete(member);
        return member;
    }

    public Member getMemberById(Long id) {
        System.out.println("get member " + id);
        return memberRepository.findById(id).get();
    }

    public List<Member> getMemberByNameContaining(String name) {
        List<Member> list = memberRepository.findByNameContaining(name);
        return list;
    }
}
