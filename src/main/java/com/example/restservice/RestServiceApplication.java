package com.example.restservice;

import javax.transaction.Transactional;

import com.example.restservice.entity.Item;
import com.example.restservice.entity.Member;
import com.example.restservice.entity.MemberItem;
import com.example.restservice.entity.Pet;
import com.example.restservice.entity.Test;
import com.example.restservice.repository.ItemRepository;
import com.example.restservice.repository.MemberItemRepository;
import com.example.restservice.repository.MemberRepository;
import com.example.restservice.repository.PetRepository;
import com.example.restservice.repository.TestRepository;
import com.example.restservice.entity.Item.ItemBuilder;
import com.example.restservice.entity.Member.MemberBuilder;
import com.example.restservice.entity.MemberItem.MemberItemBuilder;
import com.example.restservice.entity.Pet.PetBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class RestServiceApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(RestServiceApplication.class, args);
	}

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
	
	@Override
	@Transactional
	public void run(String... args) throws Exception {
        Member mem = new MemberBuilder().name("강용하").age(32).build();
        memberRepository.save(mem);
        memberRepository.save(new MemberBuilder().name("강소진").age(22).build());
        memberRepository.save(new MemberBuilder().name("김선영").age(57).build());
        memberRepository.save(new MemberBuilder().name("강동수").age(62).build());
        memberRepository.save(new MemberBuilder().name("강소라").age(31).build());
        
        memberRepository.findByName("강용하").stream()
            .forEach(member -> {
                Pet pet = new PetBuilder().name("송이").cute(100).build();
                pet.setMember(member);
                petRepository.save(pet);
                member.setName("happy " + member.getName());
                memberRepository.save(member);
        });
        memberRepository.findByName("강소진").stream()
            .forEach(member -> {
                Pet pet = new PetBuilder().name("보라").cute(90).build();
                pet.setMember(member);
                petRepository.save(pet);
                member.setName("happy " + member.getName());
                memberRepository.save(member);
        });

        String[] itemList = {"칼", "방패", "옷", "신발"}; 
        
        int power = 1;
        for (String str : itemList) {
            Item item = new ItemBuilder().name(str).power(power++).build();
            MemberItem memberItem = new MemberItemBuilder().member(mem).item(item).build();
            itemRepository.save(item);
            memberItemRepository.save(memberItem);
        }

		Test test = new Test();
		test.setText("test text");
		testRepository.save(test);
	}

}