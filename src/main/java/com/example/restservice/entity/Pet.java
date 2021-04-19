package com.example.restservice.entity;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.ColumnDefault;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@NoArgsConstructor
@Data
@Entity
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Column(nullable = false, unique = true)
    @ColumnDefault(value = "'defaultName'")
    private String name;

    @NonNull
    @Column(nullable = false)
    @ColumnDefault(value = "0")
    private Integer cute;

    @NonNull
    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "member_id")
    private Member member;

    public static class PetBuilder {
        private String name;
        private Integer cute;

        public PetBuilder name(String name) {
            this.name = name;
            return this;
        }
        public PetBuilder cute(Integer cute) {
            this.cute = cute;
            return this;
        }

        public Pet build() {
            return new Pet(this);
        }
    }

    private Pet(PetBuilder petBuilder) {
        name = petBuilder.name;
        cute = petBuilder.cute;
    }
}
