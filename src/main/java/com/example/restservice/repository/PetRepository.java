package com.example.restservice.repository;

import java.util.List;
import java.util.Optional;

import com.example.restservice.entity.Pet;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepository extends JpaRepository<Pet, Long> {
    
    Optional<Pet> findById(Long id);

    List<Pet> findByName(String name);

}