package com.example.restservice.repository;

import com.example.restservice.entity.Test;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<Test, Long> {
    
}
