package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Member;
import com.example.demo.repository.MemberRespository;
import com.example.demo.request.MemberRequest;
import com.example.demo.service.MemberService;

@Service
public class MemberServiceImpl implements MemberService{

	@Autowired
	private MemberRespository memberRepository; 
	
	public List<Member> getAllMembers() {
		return memberRepository.findAll();
	}
	
	public Member getMemberById(Integer id){
		return memberRepository.findById(id).get();
	}

	public String saveMember(MemberRequest memberRequest){
		Member member = new Member.MemberBuilder().withName(memberRequest.getName()).build();
		memberRepository.save(member);
		return "Member Saved Successfully!";
	}
}
