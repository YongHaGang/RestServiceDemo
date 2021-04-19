package com.example.restservice.entity;

import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

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
public class Item {
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
    private Integer power;

    @JsonIgnore
    @NonNull
    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private List<MemberItem> memberItem;


    public static class ItemBuilder {
        private String name;
        private Integer power;

        public ItemBuilder name(String name) {
            this.name = name;
            return this;
        }
        public ItemBuilder power(Integer power) {
            this.power = power;
            return this;
        }

        public Item build() {
            return new Item(this);
        }
    }

    private Item(ItemBuilder itemBuilder) {
        name = itemBuilder.name;
        power = itemBuilder.power;
    }
}
