package com.example.restservice.entity;

import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.lang.NonNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, include = "all")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Column(nullable = false, unique = true, length = 50)
    @ColumnDefault(value = "'defaultName'")
    private String name;

    @NonNull
    @Column(nullable = false)
    @ColumnDefault(value = "0")
    private Integer age;

    @NonNull
    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private List<MemberItem> items;

    @NonNull
    @OneToOne(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Pet pet;

    public static class MemberBuilder {
        private String name;
        private Integer age;

        public MemberBuilder name(String name) {
            this.name = name;
            return this;
        }
        public MemberBuilder age(Integer age) {
            this.age = age;
            return this;
        }

        public Member build() {
            return new Member(this);
        }
    }

    private Member(MemberBuilder memberBuilder) {
        name = memberBuilder.name;
        age = memberBuilder.age;
    }
}
