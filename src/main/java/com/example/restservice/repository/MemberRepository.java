package com.example.restservice.repository;

import java.util.List;
import java.util.Optional;

import com.example.restservice.entity.Member;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;

import javax.persistence.QueryHint;


public interface MemberRepository extends JpaRepository<Member, Long> {

    @Override
    @EntityGraph(attributePaths = {"pet", "items", "items.item"})
    List<Member> findAll();
    
    //@EntityGraph(attributePaths = {"pet", "items", "items.item"})
    //@Query("SELECT DISTINCT a FROM Member a WHERE a.id = ?1")
    Optional<Member> findById(Long id);

    List<Member> findByName(String name);

    @EntityGraph(attributePaths = {"pet", "items", "items.item"})
    List<Member> findByNameContaining(String name);

}