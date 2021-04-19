package com.example.restservice.entity;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@NoArgsConstructor
@Data
@Entity
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class MemberItem {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    @JsonIgnore
    private Member member;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;


    public static class MemberItemBuilder {
        private Member member;
        private Item item;

        public MemberItemBuilder member(Member member) {
            this.member = member;
            return this;
        }
        public MemberItemBuilder item(Item item) {
            this.item = item;
            return this;
        }

        public MemberItem build() {
            return new MemberItem(this);
        }
    }

    private MemberItem(MemberItemBuilder memberItemBuilder) {
        member = memberItemBuilder.member;
        item = memberItemBuilder.item;
    }
}
