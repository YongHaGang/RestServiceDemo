package com.example.restservice.repository;

import com.example.restservice.entity.MemberItem;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberItemRepository extends JpaRepository<MemberItem, Long> {

}